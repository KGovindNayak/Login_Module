package customlogin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import customlogin.dto.UserDto;
import customlogin.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;
	
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
    	UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
    	model.addAttribute("userdetails", userDetails);
    	return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register") // Use PostMapping for saving
    public String registerSave(@ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/register?success";
    }
}
