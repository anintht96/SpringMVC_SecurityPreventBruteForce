package spring.mvc.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import spring.mvc.utils.RequestUtils;

@Component
public class AuthencationSuccessEventListener
		implements ApplicationListener<AuthenticationSuccessEvent>, AuthenticationSuccessHandler {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		loginAttemptService.loginSucceeded(RequestUtils.getClientIP(request));
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(roles.contains("ROLE_ADMIN")) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/admin");
		}
	}

}
