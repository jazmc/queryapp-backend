package com.example.queryappbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.queryappbackend.domain.MaintainerUser;
import com.example.queryappbackend.domain.MaintainerUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService{
	private final MaintainerUserRepository repository;
	@Autowired
	public UserDetailServiceImpl(MaintainerUserRepository userRepository) {
		repository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MaintainerUser currentUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User
				(username, currentUser.getPasswordHash(), AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}
	
}
