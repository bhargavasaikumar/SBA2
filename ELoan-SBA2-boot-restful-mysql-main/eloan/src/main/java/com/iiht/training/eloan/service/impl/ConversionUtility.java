package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.dto.SanctionOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.entity.SanctionInfo;
import com.iiht.training.eloan.entity.Users;

public class ConversionUtility {

	public ConversionUtility() {
		super();
		// TODO Auto-generated constructor stub
	}

	// User Conversion from DTO to Entity
	public static Users ConvertUserDtoToUsers(UserDto userDto, String role) {

		Users users = new Users(role);
		users.setFirstName(userDto.getFirstName());
		users.setLastName(userDto.getLastName());
		users.setEmail(userDto.getEmail());
		users.setMobile(userDto.getMobile());
		users.setRole(role);

		return users;
	}

	// User Conversion from Entity to DTO
	public static UserDto ConvertUsersToUserDto(Users users) {

		UserDto userDto = new UserDto();
		userDto.setFirstName(users.getFirstName());
		userDto.setLastName(users.getLastName());
		userDto.setEmail(users.getEmail());
		userDto.setMobile(users.getMobile());

		return userDto;
	}

	// List Users Conversion from Entity to DTO
	public static List<UserDto> ConvertUsersToUserDto(List<Users> users) {

		List<UserDto> usersDto = new ArrayList<UserDto>();
		for (Users user : users) {
			UserDto userDto = new UserDto();
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setEmail(user.getEmail());
			userDto.setMobile(user.getMobile());
			usersDto.add(userDto);
		}
		return usersDto;
	}

	// Loan Conversion from Entity to DTO
	public static LoanDto ConvertLoanToLoanDto(Loan loan) {
		LoanDto loanDto = new LoanDto();
		loanDto.setLoanName(loan.getLoanName());
		loanDto.setLoanAmount(loan.getLoanAmount());
		loanDto.setLoanApplicationDate(loan.getLoanApplicationDate());
		loanDto.setBusinessStructure(loan.getBusinessStructure());
		loanDto.setBillingIndicator(loan.getBillingIndicator());
		loanDto.setTaxIndicator(loan.getTaxIndicator());

		return loanDto;
	}

	// Loan Conversion from DTO to Entity
	public static Loan ConvertLoanDtoToLoan(LoanDto loanDto, Long CustomerId, int status, String remark) {
		Loan loan = new Loan(CustomerId);
		loan.setCustomerId(CustomerId);
		loan.setLoanName(loanDto.getLoanName());
		loan.setLoanAmount(loanDto.getLoanAmount());
		loan.setLoanApplicationDate(loanDto.getLoanApplicationDate());
		loan.setBusinessStructure(loanDto.getBusinessStructure());
		loan.setBillingIndicator(loanDto.getBillingIndicator());
		loan.setTaxIndicator(loanDto.getTaxIndicator());
		loan.setStatus(status);
		loan.setRemark(remark);

		return loan;
	}

	// ProcessingInfo Conversion to DTO
	public static ProcessingDto PiConvertToPiDto(ProcessingInfo processingInfo) {
		ProcessingDto pd = new ProcessingDto();
		pd.setAcresOfLand(processingInfo.getAcresOfLand());
		pd.setLandValue(processingInfo.getLandValue());
		pd.setAppraisedBy(processingInfo.getAppraisedBy());
		pd.setValuationDate(processingInfo.getValuationDate());
		pd.setAddressOfProperty(processingInfo.getAddressOfProperty());
		pd.setSuggestedAmountOfLoan(processingInfo.getSuggestedAmountOfLoan());
		return pd;
	}

	// ProcessingInfoDto conversion to ProcessingInfo
	public static ProcessingInfo PiDtoConvertToPi(ProcessingDto procDto, Long clerkId, Long loanAppId) {
		ProcessingInfo pi = new ProcessingInfo();
		pi.setLoanAppId(loanAppId);
		pi.setLoanClerkId(clerkId);
		pi.setAcresOfLand(procDto.getAcresOfLand());
		pi.setLandValue(procDto.getLandValue());
		pi.setAppraisedBy(procDto.getAppraisedBy());
		pi.setValuationDate(procDto.getValuationDate());
		pi.setAddressOfProperty(procDto.getAddressOfProperty());
		pi.setSuggestedAmountOfLoan(procDto.getSuggestedAmountOfLoan());
		return pi;
	}

	// SanctionInfo Conversion to DTO
	public static SanctionOutputDto SiConvertToSiDto(SanctionInfo sanInf) {
		SanctionOutputDto sod = new SanctionOutputDto();
		sod.setLoanAmountSanctioned(sanInf.getLoanAmountSanctioned());
		sod.setPaymentStartDate(sanInf.getPaymentStartDate());
		sod.setTermOfLoan(sanInf.getTermOfLoan());
		sod.setLoanClosureDate(sanInf.getLoanClosureDate());
		sod.setMonthlyPayment(sanInf.getMonthlyPayment());
		return sod;
	}

	// SanctionOutputDto Conversion to SanctionInfo
	public static SanctionInfo SODtoConvertToSi(SanctionOutputDto sod, Long loanAppId, Long managerId) {
		SanctionInfo si = new SanctionInfo();
		si.setLoanAmountSanctioned(sod.getLoanAmountSanctioned());
		si.setLoanAppId(loanAppId);
		si.setLoanClosureDate(sod.getLoanClosureDate());
		si.setManagerId(managerId);
		si.setMonthlyPayment(sod.getMonthlyPayment());
		si.setPaymentStartDate(sod.getPaymentStartDate());
		si.setTermOfLoan(sod.getTermOfLoan());
		return si;
	}

}
