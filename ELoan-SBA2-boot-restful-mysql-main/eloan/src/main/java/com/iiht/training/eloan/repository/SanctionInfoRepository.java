package com.iiht.training.eloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.SanctionInfo;

@Repository
public interface SanctionInfoRepository extends JpaRepository<SanctionInfo, Long> {

	@Query("Select s From SanctionInfo s where s.loanAppId = ?1")
	public SanctionInfo getSanctionInfoByLoanAppId(Long loanAppId);

	@Query("Select count(s) From SanctionInfo s where s.loanAppId = ?1")
	public int isSancRecordAvailable(Long loanAppId);

}
