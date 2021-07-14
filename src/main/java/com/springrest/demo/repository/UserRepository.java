package com.springrest.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springrest.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Modifying
	@Query("update User set status=:status where id=:userId")
	void deleteByUserId(Integer userId, boolean status);

	@Query("from com.springrest.demo.model.User user where user.firstName=:firstName")
	List<User> findByFirstName(String firstName);

	@Query("from com.springrest.demo.model.User user where user.pincode=:pincode")
	List<User> findByPincode(int pincode);

	@Query("from com.springrest.demo.model.User user where user.lastName=:lastName")
	List<User> findByLastName(String lastName);

	
	@Query("from com.springrest.demo.model.User user order by dob asc")
	List<User> sortByDOB();

	@Query("from com.springrest.demo.model.User user order by joiningDate asc")
	List<User> sortByJoingDate();

}
