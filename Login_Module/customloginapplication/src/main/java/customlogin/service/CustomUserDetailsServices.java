package customlogin.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import customlogin.model.User;
import customlogin.repositories.UserRepository;


@Service
public class CustomUserDetailsServices implements UserDetailsService{

	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			
			throw new UsernameNotFoundException("Username or password not found");
			
		}
		
		return new CustomUserDetails(
				user.getUsername(), 
				user.getPassword(),
				authorities(),
				user.getFullname());
	}
	
	public Collection<? extends GrantedAuthority> authorities() {
		
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}
