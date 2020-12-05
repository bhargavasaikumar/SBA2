package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.exception.AlreadyProcessedException;
import com.iiht.training.eloan.exception.ClerkNotFoundException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;

	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;

	@Override
	public List<LoanOutputDto> allAppliedLoans() {
		List<LoanOutputDto> lodList = new ArrayList<LoanOutputDto>();
		for (Loan ln : loanRepository.getAllAppliedLoans(0)) {
			LoanOutputDto lod = new LoanOutputDto();
			lod.setCustomerId(ln.getCustomerId());
			lod.setLoanAppId(ln.getId());
			lod.setUserDto(pProcessingInfoRepository.isProcRecordAvailable(ln.getId()) > 0
					? ConversionUtility.ConvertUsersToUserDto(usersRepository.findUserProfileById(ln.getCustomerId()))
					: null);
			lod.setLoanDto(ConversionUtility.ConvertLoanToLoanDto(loanRepository.getLoanProfileById(ln.getId())));
			lod.setProcessingDto(pProcessingInfoRepository.isProcRecordAvailable(ln.getId()) > 0
					? ConversionUtility.PiConvertToPiDto(pProcessingInfoRepository.getProcInfoByLoanAppId(ln.getId()))
					: null);
			lod.setSanctionOutputDto(sanctionInfoRepository.isSancRecordAvailable(ln.getId()) > 0
					? ConversionUtility.SiConvertToSiDto(sanctionInfoRepository.getSanctionInfoByLoanAppId(ln.getId()))
					: null);
			lod.setStatus(ln.getStatus().toString());
			lod.setRemark(ln.getRemark());
			lodList.add(lod);
		}
		return lodList;
	}

	@Override
	public ProcessingDto processLoan(Long clerkId, Long loanAppId, ProcessingDto processingDto)
			throws AlreadyProcessedException, ClerkNotFoundException, LoanNotFoundException, InvalidDataException {

		if (usersRepository.isRoleValid(clerkId, "Clerk") == 0) {
			throw new ClerkNotFoundException("ClerkId is invalid");
		}
		if (!(loanRepository.existsById(loanAppId))) {
			throw new LoanNotFoundException("LoanAppId is invalid");
		}
		if (!(processingDto.isProcessingDtoValid(processingDto))) {
			throw new InvalidDataException("Processing Information is invalid");
		}

		if (pProcessingInfoRepository.isProcRecordAvailable(loanAppId) == 0) {
			pProcessingInfoRepository
					.saveAndFlush(ConversionUtility.PiDtoConvertToPi(processingDto, clerkId, loanAppId));
			loanRepository.setProcessStatus(loanAppId, 1);
		} else {
			throw new AlreadyProcessedException("This loan application is already processed");
		}

		return processingDto;
	}

}
