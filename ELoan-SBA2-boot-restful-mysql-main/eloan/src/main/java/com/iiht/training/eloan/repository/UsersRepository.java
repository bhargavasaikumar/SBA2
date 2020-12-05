package com.iiht.training.eloan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.iiht.training.eloan.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("Select u From Users u where u.role =?1")
	public List<Users> findAllUsersByRole(String role);

	@Query("Select u From Users u where u.id = ?1")
	public Users findUserProfileById(Long id);

	@Query("select count(u.id) from Users u where u.id = ?1 and u.role = ?2")
	public int isRoleValid(Long id, String role);

	@Query("Select count(u.email) from Users u Where u.email = ?1")
	public int isEmailExists(String email);

	@Query("Select count(u.mobile) from Users u Where u.mobile = ?1")
	public int isMobileExists(String mobile);
}
