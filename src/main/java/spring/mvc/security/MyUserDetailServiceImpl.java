package spring.mvc.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import spring.mvc.entities.User;
import spring.mvc.entities.UsersRoles;
import spring.mvc.repository.UserRepository;
import spring.mvc.repository.UsersRolesRepository;
import spring.mvc.utils.RequestUtils;

public class MyUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UsersRolesRepository usersRolesRepository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String ip = RequestUtils.getClientIP(request);
		System.out.println("IP:" + ip);
		if (loginAttemptService.isBLocked(ip)) {
			throw new RuntimeException("block_ip");
		}

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Not found Usernname: " + username);
		}
		List<UsersRoles> usersRoles = usersRolesRepository.findByUser(user);
		List<String> roles = new ArrayList<String>();
		if (!usersRoles.isEmpty()) {
			for (UsersRoles usersRoles1 : usersRoles) {
				roles.add(usersRoles1.getRole().getName());
			}
		}
		return new UserDetailImpl(user, roles);
	}

}
