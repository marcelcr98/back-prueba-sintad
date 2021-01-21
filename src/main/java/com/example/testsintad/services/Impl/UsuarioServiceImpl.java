package com.example.testsintad.services.Impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.example.testsintad.models.Usuario;
import com.example.testsintad.repositories.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepo repo;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findOneByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("El usuario no esta registrado en el sistema.", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
	
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);
		
		return ud;
	}

}