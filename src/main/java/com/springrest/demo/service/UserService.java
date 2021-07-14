package com.springrest.demo.service;

import java.util.List;
import java.util.Map;

import com.springrest.demo.dto.UserRequest;
import com.springrest.demo.model.User;

public interface UserService {

	public List<User> getAllUser();

	public Map<String, Object> createUpdate(UserRequest user);

	public User getUserById(Integer userId);

	public User deleteUserByIdSoftDelete(Integer userId);

	public User deleteUserByIdHardDelete(Integer userId);

	public Map<String, Object> searchByFirstName(String firstName);

	public Map<String, Object> searchByLastName(String lastName);

	public Map<String, Object> searchByPincode(int pincode);

	public Map<String, Object> sortByDOB();

	public Map<String, Object> sortByJoiningDate();


}
