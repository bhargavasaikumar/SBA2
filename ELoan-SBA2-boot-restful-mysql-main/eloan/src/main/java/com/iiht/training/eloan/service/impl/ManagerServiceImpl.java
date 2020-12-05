package com.iiht.training.eloan.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.RejectDto;
import com.iiht.training.eloan.dto.SanctionDto;
import com.iiht.training.eloan.dto.SanctionOutputDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.exception.AlreadyFinalizedException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.exception.ManagerNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;

	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;

	@Override
	public List<LoanOutputDto> allProcessedLoans() {
		List<LoanOutputDto> lodList = new ArrayList<LoanOutputDto>();
		for (Loan ln : loanRepository.getAllAppliedLoans(1)) {
			LoanOutputDto lod = new LoanOutputDto();
			lod.setCustomerId(ln.getCustomerId());
			lod.setLoanAppId(ln.getId());
			lod.setUserDto(pProcessingInfoRepository.isProcRecordAvailable(ln.getId()) > 0
					? ConversionUtility.ConvertUsersToUserDto(usersRepository.findUserProfileById(lod.getCustomerId()))
					: null);
			lod.setLoanDto(pProcessingInfoRepository.isProcRecordAvailable(ln.getId()) > 0
					? ConversionUtility.ConvertLoanToLoanDto(loanRepository.getLoanProfileById(lod.getLoanAppId()))
					: null);
			lod.setProcessingDto(pProcessingInfoRepository.isProcRecordAvailable(ln.getId()) > 0 ? ConversionUtility
					.PiConvertToPiDto(pProcessingInfoRepository.getProcInfoByLoanAppId(lod.getLoanAppId())) : null);
			lod.setSanctionOutputDto(sanctionInfoRepository.isSancRecordAvailable(ln.getId()) > 0 ? ConversionUtility
					.SiConvertToSiDto(sanctionInfoRepository.getSanctionInfoByLoanAppId(lod.getLoanAppId())) : null);
			lod.setStatus(ln.getStatus().toString());
			lod.setRemark(ln.getRemark());
			lodList.add(lod);
		}
		return lodList;
		
	@Override
	public RejectDto rejectLoan(Long managerId, Long loanAppId, RejectDto rejectDto)
			throws ManagerNotFoundException, LoanNotFoundException, AlreadyFinalizedException {

		if (usersRepository.isRoleValid(managerId, "Manager") == 0) {
			throw new ManagerNotFoundException("ManagerID is not correct");
		}
		if (pProcessingInfoRepository.isProcRecordAvailable(loanAppId) == 0) {
			throw new LoanNotFoundException("Either Loan ID is not found or it is not yet processed");
		}
		if (Integer.parseInt(loanRepository.getLoanStatusById(loanAppId)) == -1
				|| Integer.parseInt(loanRepository.getLoanStatusById(loanAppId)) == 2) {
			throw new AlreadyFinalizedException("Loan is already been either Sanctioned or Rejected");
		}

		loanRepository.setProcessStatus(loanAppId, -1);
		loanRepository.setProcessRemark(loanAppId, rejectDto.getRemark());
		return rejectDto;
	}

	@Override
	public SanctionOutputDto sanctionLoan(Long managerId, Long loanAppId, SanctionDto sanctionDto)
			throws ManagerNotFoundException, LoanNotFoundException, AlreadyFinalizedException {

		SanctionOutputDto sod = new SanctionOutputDto();
		SanctionDto sd = new SanctionDto();
		if (usersRepository.isRoleValid(managerId, "Manager") == 0) {
			throw new ManagerNotFoundException("ManagerID is not correct");
		}
		if (pProcessingInfoRepository.isProcRecordAvailable(loanAppId) == 0) {
			throw new LoanNotFoundException("Either Loan ID is not found or it is not yet processed");
		}
		if (Integer.parseInt(loanRepository.getLoanStatusById(loanAppId)) == -1
				|| Integer.parseInt(loanRepository.getLoanStatusById(loanAppId)) == 2) {
			throw new AlreadyFinalizedException("Loan is already been either Sanctioned or Rejected");
		}
		if (!sd.isSanctionDtoValid(sanctionDto)) {
			throw new InvalidDataException("Invalid sanction details provided");
		}

		sod.setLoanAmountSanctioned(sanctionDto.getLoanAmountSanctioned());
		LocalDate localDate = LocalDate.parse(sanctionDto.getPaymentStartDate());
		LocalDate LoanClosureDate = localDate.plusMonths(sanctionDto.getTermOfLoan().intValue());
		sod.setLoanClosureDate(LoanClosureDate.toString());
		sod.setMonthlyPayment(sanctionDto.getLoanAmountSanctioned() / sanctionDto.getTermOfLoan());
		sod.setPaymentStartDate(sanctionDto.getPaymentStartDate());
		sod.setTermOfLoan(sanctionDto.getTermOfLoan());

		loanRepository.setProcessStatus(loanAppId, 2);
		loanRepository.setProcessRemark(loanAppId, "Sanctioned");
		sanctionInfoRepository.saveAndFlush(ConversionUtility.SODtoConvertToSi(sod, loanAppId, managerId));
		return sod;
	}

}
