package xyz.blog.thejaysics.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.blog.thejaysics.model.User;
import xyz.blog.thejaysics.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() ->
				new UsernameNotFoundException("No User Found " + username));
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				true, true, true, true,
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}
}
