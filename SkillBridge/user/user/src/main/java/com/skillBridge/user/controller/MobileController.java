package com.skillBridge.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.GenerateOtpRequest;
import com.skillBridge.user.dto.VerifyOtpRegisterRequest;
import com.skillBridge.user.service.MobileService;

@RestController
@RequestMapping("/mapi/")
public class MobileController {

	@Autowired
	private MobileService mobileService;

	@PostMapping("signupGenerateOtp")
	public ResponseEntity<CommonResponse> signupGenerateOtp(@RequestBody GenerateOtpRequest req) {

		return ResponseEntity.ok(mobileService.generateSignupOtp(req.getMobileNo()));
	}

	@PostMapping("signupVerifyOtp")
	public ResponseEntity<CommonResponse> signupVerifyOtp(@RequestBody VerifyOtpRegisterRequest req) {

		return ResponseEntity.ok(mobileService.verifyOtpAndRegister(req));
	}

	@PostMapping("loginGenerateOtp")
	public ResponseEntity<CommonResponse> loginGenerateOtp(@RequestBody GenerateOtpRequest req) {

		return ResponseEntity.ok(mobileService.generateLoginOtp(req.getMobileNo()));
	}

	@PostMapping("loginVerifyOtp")
	public ResponseEntity<CommonResponse> loginVerifyOtp(@RequestBody Map<String, Object> req) {

		Long mobileNo = Long.valueOf(req.get("mobileNo").toString());
		String otp = req.get("otp").toString();

		return ResponseEntity.ok(mobileService.verifyOtpAndLogin(mobileNo, otp));
	}
}
