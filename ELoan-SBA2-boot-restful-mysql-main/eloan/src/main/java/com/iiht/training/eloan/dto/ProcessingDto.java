package com.iiht.training.eloan.dto;

import com.iiht.training.eloan.exception.InvalidDataException;

public class ProcessingDto {
	private Double acresOfLand;
	private Double landValue;
	private String appraisedBy;
	private String valuationDate;
	private String addressOfProperty;
	private Double suggestedAmountOfLoan;

	public ProcessingDto(Double acresOfLand, Double landValue, String appraisedBy, String valuationDate,
			String addressOfProperty, Double suggestedAmountOfLoan) {
		super();
		this.acresOfLand = acresOfLand;
		this.landValue = landValue;
		this.appraisedBy = appraisedBy;
		this.valuationDate = valuationDate;
		this.addressOfProperty = addressOfProperty;
		this.suggestedAmountOfLoan = suggestedAmountOfLoan;
	}

	public ProcessingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double getAcresOfLand() {
		return acresOfLand;
	}

	public void setAcresOfLand(Double acresOfLand) {
		this.acresOfLand = acresOfLand;
	}

	public Double getLandValue() {
		return landValue;
	}

	public void setLandValue(Double landValue) {
		this.landValue = landValue;
	}

	public String getAppraisedBy() {
		return appraisedBy;
	}

	public void setAppraisedBy(String appraisedBy) {
		this.appraisedBy = appraisedBy;
	}

	public String getValuationDate() {
		return valuationDate;
	}

	public void setValuationDate(String valuationDate) {
		this.valuationDate = valuationDate;
	}

	public String getAddressOfProperty() {
		return addressOfProperty;
	}

	public void setAddressOfProperty(String addressOfProperty) {
		this.addressOfProperty = addressOfProperty;
	}

	public Double getSuggestedAmountOfLoan() {
		return suggestedAmountOfLoan;
	}

	public void setSuggestedAmountOfLoan(Double suggestedAmountOfLoan) {
		this.suggestedAmountOfLoan = suggestedAmountOfLoan;
	}

	// Processing Details Validation
	public boolean isAcresOfLandValid(ProcessingDto procDto) throws InvalidDataException {
		boolean isValid = false;
		if (procDto.getAcresOfLand() != null && procDto.getAcresOfLand() > 0) {
			isValid = true;
		} else {
			throw new InvalidDataException("Acres of Land cannot be null and must be greater than 0");
		}
		return isValid;
	}

	public boolean isLandValueValid(ProcessingDto procDto) throws InvalidDataException {
		boolean isValid = false;
		if (procDto.getLandValue() != null && procDto.getLandValue() > 0) {
			isValid = true;
		} else {
			throw new InvalidDataException("Land value cannot be null and must be greater than 0");
		}
		return isValid;
	}

	public boolean isAppraisedByValid(ProcessingDto procDto) throws InvalidDataException {
		boolean isValid = false;
		if (procDto.getAppraisedBy() != null) {
			isValid = true;
		} else {
			throw new InvalidDataException("AppraisedBy cannot be null");
		}
		return isValid;
	}

	public boolean isAddressOfPropertyValid(ProcessingDto procDto) throws InvalidDataException {
		boolean isValid = false;
		if (procDto.getAddressOfProperty() != null && procDto.getAddressOfProperty().length() > 2
				&& procDto.getAddressOfProperty().length() < 151) {
			isValid = true;
		} else {
			throw new InvalidDataException(
					"Address of Property cannot be null and must be between 3 to 150 characters in length");
		}
		return isValid;
	}

	public boolean isSuggestedAmountOfLoanValid(ProcessingDto procDto) throws InvalidDataException {
		boolean isValid = false;
		if (procDto.getSuggestedAmountOfLoan() != null && procDto.getSuggestedAmountOfLoan() > 0) {
			isValid = true;
		} else {
			throw new InvalidDataException("Suggested Loan Amount cannot be null and must be greater than 0");
		}
		return isValid;
	}

	public boolean isProcessingDtoValid(ProcessingDto procDto) throws InvalidDataException {
		boolean isValid = false;
		if (procDto != null && isAcresOfLandValid(procDto) && isAddressOfPropertyValid(procDto)
				&& isAppraisedByValid(procDto) && isLandValueValid(procDto) && isSuggestedAmountOfLoanValid(procDto)) {
			isValid = true;
		} else {
			throw new InvalidDataException("Processing details provided are invalid");
		}
		return isValid;
	}
}
