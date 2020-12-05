package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.exception.CustomerNotFoundException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;

	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;

	@Override
	public UserDto register(UserDto userDto) throws InvalidDataException {
		if (userDto != null && userDto.isUserValid(userDto)) {
			if (usersRepository.isEmailExists(userDto.getEmail()) > 0) {
				throw new InvalidDataException("Email already in use, please choose other email");
			} else if (usersRepository.isMobileExists(userDto.getMobile()) > 0) {
				throw new InvalidDataException("Mobile number already in use, please choose other mobile number");
			} else {
				usersRepository.saveAndFlush(ConversionUtility.ConvertUserDtoToUsers(userDto, "Customer"));
			}
		} else {
			throw new InvalidDataException("Customer details provided are invalid");
		}
		return userDto;
	}

	@Override
	public LoanOutputDto applyLoan(Long customerId, LoanDto loanDto) throws InvalidDataException {

		LoanOutputDto LOD = new LoanOutputDto();
		if (loanDto != null && loanDto.isLoanDetailsValid(loanDto)) {
			if (usersRepository.findById(customerId) != null && customerId != null) {
				loanRepository.saveAndFlush(
						ConversionUtility.ConvertLoanDtoToLoan(loanDto, customerId, 0, "New Loan Applied"));
				LOD.setCustomerId(customerId);
				LOD.setLoanAppId(loanRepository.getRecentLoanAppId(customerId));
				LOD.setUserDto(
						ConversionUtility.ConvertUsersToUserDto(usersRepository.findUserProfileById(customerId)));
				LOD.setLoanDto(loanDto);
				LOD.setStatus("0");
				LOD.setRemark("New Loan Applied");
			} else {
				throw new InvalidDataException("CustomerID is not available");
			}
		} else {
			throw new InvalidDataException("Loan Details should not be null and must be valid");
		}
		return LOD;
	}

	@Override
	public LoanOutputDto getStatus(Long loanAppId) throws LoanNotFoundException {
		LoanOutputDto lod = new LoanOutputDto();
		if (loanRepository.existsById(loanAppId)) {
			lod.setCustomerId(loanRepository.getLoanCustomerIdById(loanAppId));
			lod.setLoanAppId(loanAppId);
			lod.setUserDto(
					ConversionUtility.ConvertUsersToUserDto(usersRepository.findUserProfileById(lod.getCustomerId())));
			lod.setLoanDto(ConversionUtility.ConvertLoanToLoanDto(loanRepository.getLoanProfileById(loanAppId)));
			lod.setProcessingDto(pProcessingInfoRepository.isProcRecordAvailable(loanAppId) > 0
					? ConversionUtility.PiConvertToPiDto(pProcessingInfoRepository.getProcInfoByLoanAppId(loanAppId))
					: null);
			lod.setSanctionOutputDto(sanctionInfoRepository.isSancRecordAvailable(loanAppId) > 0
					? ConversionUtility.SiConvertToSiDto(sanctionInfoRepository.getSanctionInfoByLoanAppId(loanAppId))
					: null);
			lod.setStatus(loanRepository.getLoanStatusById(loanAppId));
			lod.setRemark(loanRepository.getLoanRemarkById(loanAppId));
		} else {
			throw new LoanNotFoundException("Loan is not available");
		}
		return lod;
	}

	@Override
	public List<LoanOutputDto> getStatusAll(Long customerId) throws CustomerNotFoundException {
		List<LoanOutputDto> lodList = new ArrayList<LoanOutputDto>();
		if (customerId != null && loanRepository.isCustomerIdPresent(customerId) > 0) {
			for (Loan i : loanRepository.getLoanAppIdByCustomerId(customerId)) {
				LoanOutputDto lod = new LoanOutputDto();
				lod.setCustomerId(loanRepository.getLoanCustomerIdById(i.getId()));
				lod.setLoanAppId(i.getId());
				lod.setUserDto(ConversionUtility
						.ConvertUsersToUserDto(usersRepository.findUserProfileById(lod.getCustomerId())));
				lod.setLoanDto(ConversionUtility.ConvertLoanToLoanDto(loanRepository.getLoanProfileById(i.getId())));
				lod.setProcessingDto(pProcessingInfoRepository.isProcRecordAvailable(i.getId()) > 0 ? ConversionUtility
						.PiConvertToPiDto(pProcessingInfoRepository.getProcInfoByLoanAppId(i.getId())) : null);
				lod.setSanctionOutputDto(sanctionInfoRepository.isSancRecordAvailable(i.getId()) > 0 ? ConversionUtility
						.SiConvertToSiDto(sanctionInfoRepository.getSanctionInfoByLoanAppId(i.getId())) : null);
				lod.setStatus(loanRepository.getLoanStatusById(i.getId()));
				lod.setRemark(loanRepository.getLoanRemarkById(i.getId()));
				lodList.add(lod);
			}
		} else {
			throw new CustomerNotFoundException("CustomerId not present");
		}
		return lodList;
	}

}
