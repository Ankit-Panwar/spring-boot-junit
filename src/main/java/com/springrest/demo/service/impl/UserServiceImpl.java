package com.springrest.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.demo.dto.UserRequest;
import com.springrest.demo.model.User;
import com.springrest.demo.repository.UserRepository;
import com.springrest.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<User> getAllUser() {
		List<User> userList = this.userRepository.findAll();
		System.out.println("Getting data from DB : " + userList);
		return userList;
	}

	@Override
	public Map<String, Object> createUpdate(UserRequest userDto) {
		Map<String, Object> response = new HashMap<String, Object>();
		User user = modelMapper.map(userDto, User.class);
		user = this.userRepository.save(user);
		response.put("data", user);
		response.put("message", userDto.getId() == 0 ? "User Addes Sucessfully":"User Updated Sucessfully");
		return response;
	}

	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = this.userRepository.findById(userId);
		return user.isPresent() ? user.get():null;
	}

	@Override
	public User deleteUserByIdSoftDelete(Integer userId) {
		User user = this.userRepository.findById(userId).get();
		this.userRepository.deleteByUserId(userId, false);
		return user;
	}

	@Override
	public User deleteUserByIdHardDelete(Integer userId) {
		User user = this.userRepository.findById(userId).get();
		this.userRepository.deleteById(userId);
		return user;
	}

	@Override
	public Map<String, Object> searchByFirstName(String firstName) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> userList = userRepository.findByFirstName(firstName);
		if(userList.size() == 0)
			response.put("message", "No Record Found");
		else 
			response.put("data", userList);
		return response;
	}

	@Override
	public Map<String, Object> searchByLastName(String lastName) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> userList = userRepository.findByLastName(lastName);
		if(userList.size() == 0)
			response.put("message", "No Record Found");
		else 
			response.put("data", userList);
		return response;
	}

	@Override
	public Map<String, Object> searchByPincode(int pincode) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> userList = userRepository.findByPincode(pincode);
		if(userList.size() == 0)
			response.put("message", "No Record Found");
		else 
			response.put("data", userList);
		return response;
	}

	@Override
	public Map<String, Object> sortByDOB() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> userList = this.userRepository.sortByDOB();
		response.put("data",userList);
		return response;
	}

	@Override
	public Map<String, Object> sortByJoiningDate() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<User> userList = this.userRepository.sortByJoingDate();
		response.put("data", userList);
		return response;
	}

	
	
}
