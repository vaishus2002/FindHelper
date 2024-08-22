package com.app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.customException.ResourceNotFoundException;
import com.app.dto.JwtRequest;
import com.app.dto.JwtResponse;
import com.app.dto.UserDTO;
import com.app.security.JwtHelper;

@RestController
@RequestMapping("/auth")
@CrossOrigin("/**")
public class AuthController {
	@Autowired
 private AuthenticationManager manger;
	@Autowired
	private UserDetailsService userDetailSevice;
	@Autowired
	private JwtHelper helper;
	@Autowired
	private ModelMapper model;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		System.out.println(request);

		this.autheticateUser(request.getUsername(), request.getPassword());
		System.out.println("I Am here");
		UserDetails userDetails = this.userDetailSevice.loadUserByUsername(request.getUsername());
		System.out.println(userDetails.getUsername());
		String token = this.helper.generateToken(userDetails);
		System.out.println(token);
		JwtResponse response = new JwtResponse();
		response.setToken(token);
		response.setUser(this.model.map(userDetails, UserDTO.class));

		return new ResponseEntity<JwtResponse>(response, HttpStatus.ACCEPTED);

	}

	private void autheticateUser(String Username, String password) {
		try {
			
			manger.authenticate(new UsernamePasswordAuthenticationToken(Username, password));
			System.out.println("Authenticate User");
		} catch (BadCredentialsException e) {
			throw new ResourceNotFoundException("Invaild username or password");

		} catch (DisabledException e) {
			throw new ResourceNotFoundException("User is not active");
		}

	}

}