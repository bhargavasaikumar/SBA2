package com.iiht.training.eloan.dto;

public class RejectDto {
	private String remark;

	public RejectDto(String remark) {
		super();
		this.remark = remark;
	}

	public RejectDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
