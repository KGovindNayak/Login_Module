package customlogin.service;

import org.springframework.stereotype.Service;

import customlogin.dto.UserDto;
import customlogin.model.User;

@Service
public interface UserService {

	User findByUsername(String username);
	User save (UserDto userDto);
}
