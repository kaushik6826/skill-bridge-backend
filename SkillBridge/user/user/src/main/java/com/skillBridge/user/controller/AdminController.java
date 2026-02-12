package com.skillBridge.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillBridge.user.dto.AdminLoginRequest;
import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.CreateAdminUserRequest;
import com.skillBridge.user.service.AdminService;

@RestController
@RequestMapping("/admin/auth/")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping(value = "login/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> login(@RequestBody AdminLoginRequest req) {

		return ResponseEntity.ok(adminService.login(req));
	}

	@PostMapping(value = "createUsers/v1.0", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> createUser(@RequestBody CreateAdminUserRequest req,
			@RequestHeader("Authorization") String token) {

		return ResponseEntity.ok(adminService.createUser(req, token));
	}

	@PostMapping(value = "allowedRoles/v1.0", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> allowedRoles(@RequestHeader("Authorization") String token) {

		return ResponseEntity.ok(adminService.getAllowedRoles(token));
	}
}
