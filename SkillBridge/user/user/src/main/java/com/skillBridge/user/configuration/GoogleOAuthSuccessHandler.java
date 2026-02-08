package com.skillBridge.user.configuration;

import com.skillBridge.user.model.master.Worker;
import com.skillBridge.user.repository.WorkerRepository;
import com.skillBridge.user.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class GoogleOAuthSuccessHandler
        implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

       // Worker worker = workerRepository.findByEmail(email)

    //    String jwt = jwtUtil.generateAccessToken(worker.getId(), email);

	/*
	 * response.sendRedirect( "http://localhost:3000/login-success?token=" + jwt );
	 */
    }
}

