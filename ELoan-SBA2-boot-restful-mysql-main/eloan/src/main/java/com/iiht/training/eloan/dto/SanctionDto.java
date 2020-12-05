package com.iiht.training.eloan.dto;

import com.iiht.training.eloan.exception.InvalidDataException;

public class SanctionDto {
	private Double loanAmountSanctioned;
	private Double termOfLoan;
	private String paymentStartDate;

	public SanctionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanctionDto(Double loanAmountSanctioned, Double termOfLoan, String paymentStartDate) {
		super();
		this.loanAmountSanctioned = loanAmountSanctioned;
		this.termOfLoan = termOfLoan;
		this.paymentStartDate = paymentStartDate;
	}

	public Double getLoanAmountSanctioned() {
		return loanAmountSanctioned;
	}

	public void setLoanAmountSanctioned(Double loanAmountSanctioned) {
		this.loanAmountSanctioned = loanAmountSanctioned;
	}

	public Double getTermOfLoan() {
		return termOfLoan;
	}

	public void setTermOfLoan(Double termOfLoan) {
		this.termOfLoan = termOfLoan;
	}

	public String getPaymentStartDate() {
		return paymentStartDate;
	}

	public void setPaymentStartDate(String paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}

	// SanctionDTO Validations
	public boolean isLoanAmountSanctionedValid(SanctionDto sdto) throws InvalidDataException {
		boolean isValid = false;
		if (sdto.getLoanAmountSanctioned() != null && sdto.getLoanAmountSanctioned() > 0) {
			isValid = true;
		} else {
			throw new InvalidDataException("LoanAmountSanctioned cannot be null and must be greater than 0");
		}
		return isValid;
	}

	public boolean isTermOfLoanValid(SanctionDto sdto) throws InvalidDataException {
		boolean isValid = false;
		if (sdto.getTermOfLoan() != null && sdto.getTermOfLoan() > 0) {
			isValid = true;
		} else {
			throw new InvalidDataException("TermOfLoan cannot be null and must be greater than 0");
		}
		return isValid;
	}

	public boolean isSanctionDtoValid(SanctionDto sdto) throws InvalidDataException {
		boolean isValid = false;
		if (sdto != null && isTermOfLoanValid(sdto) && isLoanAmountSanctionedValid(sdto)) {
			isValid = true;
		} else {
			throw new InvalidDataException("Incorrect sanction details provided");
		}
		return isValid;
	}
}
