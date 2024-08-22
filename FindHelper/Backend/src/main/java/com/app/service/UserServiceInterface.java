package com.app.service;

import com.app.dto.UserDTO;
import com.app.entity.Role;
import com.app.entity.User;

public interface UserServiceInterface {
	public UserDTO registerUser(UserDTO userDTO);

	public UserDTO loginUser(String email, String password, Role role);

	public User findById(Long id);
}
