package com.spring.web.service.Impl;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.web.dto.UserDTO;
import com.spring.web.exceptions.EmailAlReady;
import com.spring.web.exceptions.UserAlReady;
import com.spring.web.mapper.UserMapper;
import com.spring.web.model.User;
import com.spring.web.repository.UserRepository;
import com.spring.web.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	

	@Override
	public List<UserDTO> getAllUser() {
		
		List<User> users = userRepository.findAll();
		return users.stream().map(
				(user) -> UserMapper.ModeltoDto(user)
				).collect(Collectors.toList());
	}


	@Override
	public UserDTO registerNewUser(UserDTO user) throws EmailAlReady, UserAlReady{
		
		if(emailExit(user.getEmail())) {
			
			
			throw new EmailAlReady();
			 
		}
		
		if(userExit(user.getUsername())) {
		
			throw new UserAlReady();
		}
		
		User newUser = UserMapper.DTOtoModel(user);
		
		userRepository.save(newUser);
		
		return UserMapper.ModeltoDto(newUser);
		
	}
	
	private boolean emailExit(String email) {
	
		return userRepository.findByEmail(email) != null; 
		
	}
	
	

	
	private boolean userExit(String username) {
		
		return userRepository.findByUsername(username) != null; 
		
		
	}




}
