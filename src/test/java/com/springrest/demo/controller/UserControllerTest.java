package com.springrest.demo.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springrest.demo.controller.UserController;
import com.springrest.demo.dto.UserRequest;
import com.springrest.demo.model.User;
import com.springrest.demo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	User user = new User(1, "Sanjay", "Rai", LocalDate.of(1996, 11, 10), LocalDate.of(1996, 11, 10), 
			0000111111L, "test@gmail.com", 001234, "b 860 new ashok nagar", true);
	List<User> mockUser = Arrays.asList(user);
	
	String exampleUserJson = "{\"firstName\":\"Rajat\",\"lastName\":\"Mishra\",\"dob\":\"1994/9/15\"}";
	
	@Test
	public void retrieveAllUserTest() throws Exception {
		Mockito.when(userService.getAllUser()).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{firstName:Ajay,lastName:Rathi,dob:1994/9/15,joiningDate:2021/7/1}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);

	}

	@Test
	public void retrieveUserByIdTest() throws Exception {
		Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{id:user1,firstName:Ajay,lastName:Rathi,dob:1994/9/15,joiningDate:2021/7/1}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);

	}
	
	@Test
	public void addUserTest() throws Exception {
		Map<String, Object> mockUser = new HashMap<String, Object>();
		mockUser.put("data", user);
		Mockito.when(userService.createUpdate(Mockito.any(UserRequest.class))).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/user")
				.accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/add/",
				response.getHeader(HttpHeaders.LOCATION));

	}
	
	@Test
	public void deleteUserTest() throws Exception {
		Mockito.when(userService.deleteUserByIdSoftDelete(Mockito.anyInt())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{id:user1,firstName:Ajay,lastName:Rathi,dob:1994/9/15,joiningDate:2021/7/1}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	

}
