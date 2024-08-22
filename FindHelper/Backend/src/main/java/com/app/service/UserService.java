package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.AuthenticationException;
import com.app.customException.ResourceNotFoundException;
import com.app.dto.UserDTO;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	// @Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		// Check if user already exists
		if (userRepository.findByEmail(userDTO.getEmail()) != null) {
			throw new AuthenticationException("User already exists with email: " + userDTO.getEmail());
		}

		// Map DTO to entity
		User user = modelMapper.map(userDTO, User.class);

		// Encode the password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Save user
		User savedUser = userRepository.save(user);

		// Map entity back to DTO
		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO loginUser(String email, String password, Role role) {
		User user = userRepository.findByEmail(email);
		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			throw new AuthenticationException("Invalid credentials");
		}
		// Verify role
		if (!((user.getRole()) == role)) {
			throw new ResourceNotFoundException("Invalid role");
		}
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null); // Fetch the user from the repository
	}
}
