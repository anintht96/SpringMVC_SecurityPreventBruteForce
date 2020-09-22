package spring.mvc.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.mvc.entities.Role;
import spring.mvc.entities.User;
import spring.mvc.entities.UsersRoles;
import spring.mvc.repository.RoleRepository;
import spring.mvc.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(@RequestParam(name = "message", required = false) String message, final Model model) {
		if (message != null && !message.isEmpty()) {
			if (message.equals("block_ip")) {
				model.addAttribute("message", "Your  IP has been blocked! Pls try again in 30 minutes");
			}
			if(message.equals("logout")) {
				model.addAttribute("message", "Logout!");
			}
			if(message.equals("error")) {
				model.addAttribute("message", "Login Faild!");
			}
		}
		return "login";
	}
	
	@RequestMapping(value = "/admin",method=RequestMethod.GET)
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/user")
	public String user() {
		return "user";
	}
	
	@RequestMapping(value = "/register",method=RequestMethod.GET)
	public String getRegister(@ModelAttribute(name = "user")User user) {
		return "register";
	}
	
	@RequestMapping(value = "/register",method=RequestMethod.POST)
	public String postRegister(@ModelAttribute(name = "user")User user) {
		Role role=roleRepository.findByName("ROLE_USER");
		UsersRoles usersRoles=new UsersRoles();
		usersRoles.setRole(role);
		usersRoles.setUser(user);
		Set<UsersRoles> usersRolesSet=new HashSet<UsersRoles>();
		usersRolesSet.add(usersRoles);
		user.setUsersRoles(usersRolesSet);
		user.setEnable(true);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/403",method = RequestMethod.GET)
	public String accessDenied(final Model model,Principal principal) {
		if(principal!=null) {
			model.addAttribute("message", "Hi "+principal.getName()+"<br> Your do not have permission to access this page!");
		}else {
			model.addAttribute("message", "You do not have permission to access this page!");
		}
		return "403";
	}
}
