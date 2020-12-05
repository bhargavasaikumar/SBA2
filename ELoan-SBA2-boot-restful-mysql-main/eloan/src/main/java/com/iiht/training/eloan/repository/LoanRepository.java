package com.iiht.training.eloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.training.eloan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	@Query("Select l from Loan l where l.id = ?1")
	public Loan getLoanProfileById(Long id);

	@Query("Select l.customerId from Loan l where l.id = ?1")
	public Long getLoanCustomerIdById(Long id);

	@Query("Select l.status from Loan l where l.id = ?1")
	public String getLoanStatusById(Long id);

	@Query("Select l.remark from Loan l where l.id = ?1")
	public String getLoanRemarkById(Long id);

	@Query("Select l from Loan l where l.customerId = ?1")
	public List<Loan> getLoanAppIdByCustomerId(Long customerId);

	@Query("Select max(l.id) From Loan l where l.customerId = ?1")
	public Long getRecentLoanAppId(Long customerId);

	@Query("Select l From Loan l Where l.status = ?1")
	public List<Loan> getAllAppliedLoans(int status);

	@Query("Select count(l.id) From Loan l Where l.customerId = ?1")
	public int isCustomerIdPresent(Long customerId);

	@Transactional
	@Modifying
	@Query("Update Loan l Set l.status = ?2 Where l.id = ?1")
	public void setProcessStatus(Long id, int status);

	@Transactional
	@Modifying
	@Query("Update Loan l Set l.remark = ?2 Where l.id = ?1")
	public void setProcessRemark(Long id, String remark);
}
