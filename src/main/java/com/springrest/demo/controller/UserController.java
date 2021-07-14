package com.springrest.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.demo.dto.UserRequest;
import com.springrest.demo.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	@GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(HttpServletRequest request, @RequestBody UserRequest user){
		return ResponseEntity.ok(this.userService.createUpdate(user));
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(HttpServletRequest request, UserRequest user){
		return ResponseEntity.ok(this.userService.createUpdate(user));
	}
	
	@DeleteMapping(value = "delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteByIdSoftDelete(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(this.userService.deleteUserByIdSoftDelete(userId));
	}
	
//	@DeleteMapping(value = "user/{userId}")
//	public ResponseEntity<?> deleteByIdHardDelete(@RequestParam Integer userId){
//		return ResponseEntity.ok(this.userService.deleteUserByIdHardDelete(userId));
//	}
	
	@GetMapping(value = "search/firstName/{firstName}")
	public ResponseEntity<?> searchByFirstName(@PathVariable("firstName") String firstName){
		return ResponseEntity.ok(this.userService.searchByFirstName(firstName));
	}
	
	@GetMapping(value = "search/lastName/{lastName}")
	public ResponseEntity<?> searchByLastName(@PathVariable("lastName") String lastName){
		return ResponseEntity.ok(this.userService.searchByLastName(lastName));
	}
	
	@GetMapping(value = "search/pincode/{pincode}")
	public ResponseEntity<?> searchByPincode(@PathVariable("pincode") int pincode){
		return ResponseEntity.ok(this.userService.searchByPincode(pincode));
	}
	
	@GetMapping(value = "sort/dob")
	public ResponseEntity<?> sortByDOB(){
		return ResponseEntity.ok(this.userService.sortByDOB());
	}
	
	@GetMapping(value = "sort/joiningDate")
	public ResponseEntity<?> sortByJoiningDate(){
		return ResponseEntity.ok(this.userService.sortByJoiningDate());
	}
	
	
}
