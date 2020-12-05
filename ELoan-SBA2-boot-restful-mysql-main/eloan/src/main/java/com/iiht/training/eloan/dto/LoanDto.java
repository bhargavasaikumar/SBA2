package com.iiht.training.eloan.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iiht.training.eloan.exception.InvalidDataException;

public class LoanDto {

	@JsonProperty(value = "loanName")
	@NotNull(message = "loanName cannot be null")
	@Size(min = 3, max = 100, message = "loanName length should be in between 3 characters to 100 characters")
	private String loanName;

	@JsonProperty(value = "loanAmount")
	@NotNull(message = "loanAmount cannot be null")
	@DecimalMin(value = "1.0", message = "loanAmount cannot be 0")
	private Double loanAmount;

	@JsonProperty(value = "loanApplicationDate")
	@NotNull(message = "loanApplicationDate is required to be mentioned")
	private String loanApplicationDate;

	@JsonProperty(value = "businessStructure")
	@NotNull(message = "businessStructure cannot be null")
	private String businessStructure;

	@JsonProperty(value = "billingIndicator")
	@NotNull(message = "billingIndicator cannot be null")
	private String billingIndicator;

	@JsonProperty(value = "taxIndicator")
	@NotNull(message = "taxIndicator cannot be null")
	private String taxIndicator;

	public LoanDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanDto(String loanName, Double loanAmount, String loanApplicationDate, String businessStructure,
			String billingIndicator, String taxIndicator) {
		super();
		this.loanName = loanName;
		this.loanAmount = loanAmount;
		this.loanApplicationDate = loanApplicationDate;
		this.businessStructure = businessStructure;
		this.billingIndicator = billingIndicator;
		this.taxIndicator = taxIndicator;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanApplicationDate() {
		return loanApplicationDate;
	}

	public void setLoanApplicationDate(String loanApplicationDate) {
		this.loanApplicationDate = loanApplicationDate;
	}

	public String getBusinessStructure() {
		return businessStructure;
	}

	public void setBusinessStructure(String businessStructure) {
		this.businessStructure = businessStructure;
	}

	public String getBillingIndicator() {
		return billingIndicator;
	}

	public void setBillingIndicator(String billingIndicator) {
		this.billingIndicator = billingIndicator;
	}

	public String getTaxIndicator() {
		return taxIndicator;
	}

	public void setTaxIndicator(String taxIndicator) {
		this.taxIndicator = taxIndicator;
	}

	// Loan Details validation
	public boolean isLoanNameValid(LoanDto loanDto) throws InvalidDataException {
		boolean isValid = false;
		if (loanDto.getLoanName() != null && loanDto.getLoanName().length() > 2
				&& loanDto.getLoanName().length() < 101) {
			isValid = true;
		} else {
			throw new InvalidDataException("LoanName is invalid");
		}
		return isValid;
	}

	public boolean isLoanAmountValid(LoanDto loanDto) throws InvalidDataException {
		boolean isValid = false;
		if (loanDto.getLoanAmount() != null && loanDto.getLoanAmount() > 0) {
			isValid = true;
		} else {
			throw new InvalidDataException("LoanAmount is invalid");
		}
		return isValid;
	}

	public boolean isBusinessStructureValid(LoanDto loanDto) throws InvalidDataException {
		boolean isValid = false;
		if (loanDto.getBusinessStructure() != null && (loanDto.getBusinessStructure().equalsIgnoreCase("Individual")
				|| loanDto.getBusinessStructure().equalsIgnoreCase("Organization"))) {
			isValid = true;
		} else {
			throw new InvalidDataException(
					"Business Structure could not be blank and must be either Individual or Organization only");
		}
		return isValid;
	}

	public boolean isBillingIndicatorValid(LoanDto loanDto) throws InvalidDataException {
		boolean isValid = false;
		if (loanDto.getBillingIndicator() != null && (loanDto.getBillingIndicator().equalsIgnoreCase("Salaried")
				|| loanDto.getBillingIndicator().equalsIgnoreCase("Unpaid"))) {
			isValid = true;
		} else {
			throw new InvalidDataException(
					"Billing Indicator could not be blank and must be either Salaried or Unpaid only");
		}
		return isValid;
	}

	public boolean isTaxIndicatorvalid(LoanDto loanDto) throws InvalidDataException {
		boolean isValid = false;
		if (loanDto.getTaxIndicator() != null && (loanDto.getTaxIndicator().equalsIgnoreCase("Tax Payer")
				|| loanDto.getTaxIndicator().equalsIgnoreCase("Non Tax Payer"))) {
			isValid = true;
		} else {
			throw new InvalidDataException(
					"Tax Indicator could not be blank and must be either Tax Payer or Non Tax Payer");
		}
		return isValid;
	}

	public boolean isLoanDetailsValid(LoanDto loanDto) throws InvalidDataException {
		boolean isValid = false;
		if (isLoanNameValid(loanDto) && isLoanAmountValid(loanDto) && isBusinessStructureValid(loanDto)
				&& isBillingIndicatorValid(loanDto) && isTaxIndicatorvalid(loanDto)) {
			isValid = true;
		} else {
			throw new InvalidDataException("Loan Details provided are invalid");
		}
		return isValid;
	}
}
