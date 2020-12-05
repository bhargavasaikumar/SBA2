package com.iiht.training.eloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.ProcessingInfo;

@Repository
public interface ProcessingInfoRepository extends JpaRepository<ProcessingInfo, Long> {

	@Query("Select p From ProcessingInfo p Where p.loanAppId = ?1")
	public ProcessingInfo getProcInfoByLoanAppId(Long loanAppId);

	@Query("Select count(p.id) From ProcessingInfo p Where p.loanAppId = ?1")
	public int isProcRecordAvailable(Long loanAppId);

}
