package com.springrest.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 6000467020582231881L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 5, max = 10, message="Your name should be between 5 - 10 characters.")
	@Column(name="first_name")
	private String firstName;
	
	@Min(value = 5, message = "Please insert at least 5 characters")
	@Column(name="last_name")	
	private String lastName;

	@NotNull(message="Please select a Date of Birth")
	@Column(name="dob")
	private LocalDate dob;
	
	@NotNull(message="Please select Joining Date")
	@Column(name="joining_date")
	private LocalDate joiningDate;
	
	@NotNull(message="Please fill contact No")
    @Length(min=10, max=10, message="Contact No should be 10 charactes")
	@Column(name="contact_no", length = 10)
	private long contactNo;
	
	@Pattern(regexp=".+@.+\\..+", message="Enter valid email-Id!")
	@Column(name="email")
	private String email;
	
	@Pattern(regexp="[0-9]+", message="Wrong zip!")
	@Column(name="pincode", length = 6)
	private int pincode;
	
	@Column(name="address", length = 50)
	private String address;
	
	@Column(name = "status" , columnDefinition = "tinyint default false")
	private boolean status;
	
	
}
