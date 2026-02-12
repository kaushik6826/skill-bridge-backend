package com.skillBridge.user.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillBridge.user.dto.AdminLoginRequest;
import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.CreateAdminUserRequest;
import com.skillBridge.user.model.EmRole;
import com.skillBridge.user.model.User;
import com.skillBridge.user.repository.RoleRepository;
import com.skillBridge.user.repository.UserRepository;
import com.skillBridge.user.utils.JwtUtil;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RoleRepository roleRepo;

	private User getLoggedUser(String headerToken) {

		if (headerToken == null || !headerToken.startsWith("Bearer ")) {
			throw new RuntimeException("Token Missing");
		}

		String token = headerToken.substring(7);

		if (!jwtUtil.validateToken(token)) {
			throw new RuntimeException("Invalid or Expired Token");
		}

		Long userId = jwtUtil.extractUserId(token);

		return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("Invalid User"));
	}

	public CommonResponse login(AdminLoginRequest req) {

		if (req.getUsername() == null || req.getPassword() == null) {
			return new CommonResponse("400", "Invalid Request", null);
		}

		User user = userRepo.findByEmail(req.getUsername()).orElse(null);

		if (user == null)
			return new CommonResponse("401", "User not found", null);

		if (!Boolean.TRUE.equals(user.getIsActive()))
			return new CommonResponse("401", "User inactive", null);

		if (!user.getPassword().equals(req.getPassword()))
			return new CommonResponse("401", "Invalid password", null);

		EmRole role = roleRepo.findById(user.getRole()).orElse(null);

		if (role == null)
			return new CommonResponse("403", "Role not configured", null);

		String token = jwtUtil.generateAccessToken(user.getId(), user.getEmail());

		Map<String, Object> result = new HashMap<>();
		result.put("token", token);
		result.put("role", role.getRole());
		result.put("email", user.getEmail());

		return new CommonResponse("200", "Login Successful", result);
	}

	public CommonResponse createUser(CreateAdminUserRequest req, String headerToken) {

		User loggedUser = getLoggedUser(headerToken);

		EmRole requestedRole = roleRepo.findById(req.getRoleId())
				.orElseThrow(() -> new RuntimeException("Role not found"));

		if (requestedRole.getParentId() == null || !requestedRole.getParentId().equals(loggedUser.getRole())) {

			return new CommonResponse("403", "You cannot create this role from your hierarchy", null);
		}

		if (userRepo.findByEmail(req.getEmail()).isPresent()) {
			return new CommonResponse("400", "User already exists", null);
		}

		User newUser = new User();
		newUser.setName(req.getName());
		newUser.setEmail(req.getEmail());
		newUser.setPassword(req.getPassword());
		newUser.setRole(req.getRoleId());
		newUser.setIsActive(true);
		newUser.setCreatedAt(LocalDateTime.now());

		userRepo.save(newUser);

		return new CommonResponse("200", "User Created Successfully", null);
	}

	public CommonResponse getAllowedRoles(String headerToken) {

		User loggedUser = getLoggedUser(headerToken);

		List<EmRole> roles = roleRepo.findByParentId(loggedUser.getRole());

		return new CommonResponse("200", "Success", roles);
	}
}
