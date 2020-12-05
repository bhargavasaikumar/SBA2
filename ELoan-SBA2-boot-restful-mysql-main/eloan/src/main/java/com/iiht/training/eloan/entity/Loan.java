package com.iiht.training.eloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "customerId")
	private Long customerId;

	@Column(name = "loanName")
	private String loanName;

	@Column(name = "loanAmount")
	private Double loanAmount;

	@Column(name = "loanApplicationDate")
	private String loanApplicationDate;

	@Column(name = "businessStructure")
	private String businessStructure;

	@Column(name = "billingIndicator")
	private String billingIndicator;

	@Column(name = "taxIndicator")
	private String taxIndicator;

	@Column(name = "status")
	private Integer status;

	@Column(name = "remark")
	private String remark;

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(Long id, Long customerId, String loanName, Double loanAmount, String loanApplicationDate,
			String businessStructure, String billingIndicator, String taxIndicator, Integer status, String remark) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.loanName = loanName;
		this.loanAmount = loanAmount;
		this.loanApplicationDate = loanApplicationDate;
		this.businessStructure = businessStructure;
		this.billingIndicator = billingIndicator;
		this.taxIndicator = taxIndicator;
		this.status = status;
		this.remark = remark;
	}

	public Loan(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
