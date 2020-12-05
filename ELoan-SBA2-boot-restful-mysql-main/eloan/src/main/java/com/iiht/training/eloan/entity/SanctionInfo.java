package com.iiht.training.eloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SanctionInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loanAppId")
	private Long loanAppId;

	@Column(name = "managerId")
	private Long managerId;

	@Column(name = "loanAmountSanctioned")
	private Double loanAmountSanctioned;

	@Column(name = "termOfLoan")
	private Double termOfLoan;

	@Column(name = "paymentStartDate")
	private String paymentStartDate;

	@Column(name = "loanClosureDate")
	private String loanClosureDate;

	@Column(name = "monthlyPayment")
	private Double monthlyPayment;

	public SanctionInfo(Long id, Long loanAppId, Long managerId, Double loanAmountSanctioned, Double termOfLoan,
			String paymentStartDate, String loanClosureDate, Double monthlyPayment) {
		super();
		this.id = id;
		this.loanAppId = loanAppId;
		this.managerId = managerId;
		this.loanAmountSanctioned = loanAmountSanctioned;
		this.termOfLoan = termOfLoan;
		this.paymentStartDate = paymentStartDate;
		this.loanClosureDate = loanClosureDate;
		this.monthlyPayment = monthlyPayment;
	}

	public SanctionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoanAppId() {
		return loanAppId;
	}

	public void setLoanAppId(Long loanAppId) {
		this.loanAppId = loanAppId;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
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

	public String getLoanClosureDate() {
		return loanClosureDate;
	}

	public void setLoanClosureDate(String loanClosureDate) {
		this.loanClosureDate = loanClosureDate;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

}
