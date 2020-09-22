package spring.mvc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import spring.mvc.utils.RequestUtils;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			loginAttemptService.loginFailed(RequestUtils.getClientIP(request));
		}
		
		if (exception.getMessage() != null && exception.getMessage().equals("block_ip")) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/login?message=block_ip");
			return;
		}
		response.sendRedirect(request.getServletContext().getContextPath() + "/login?message=error");
	}

}
