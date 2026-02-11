package com.skillBridge.user.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.VerifyOtpRegisterRequest;
import com.skillBridge.user.model.User;
import com.skillBridge.user.model.UserToken;
import com.skillBridge.user.model.WorkerLoginOtp;
import com.skillBridge.user.repository.UserRepository;
import com.skillBridge.user.repository.UserTokenRepository;
import com.skillBridge.user.repository.WorkerLoginOtpRepository;
import com.skillBridge.user.utils.JwtUtil;

@Service
public class MobileService {

	@Autowired
	private WorkerLoginOtpRepository otpRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserTokenRepository tokenRepo;

	@Autowired
	private JwtUtil jwtUtil;

	private static final SecureRandom secureRandom = new SecureRandom();

	public CommonResponse generateSignupOtp(Long mobileNo) {

		String email = mobileNo + "@skillbridge.com";

		if (userRepo.findByEmail(email).isPresent()) {
			return new CommonResponse("FAILED", "User already registered. Please login.", null);
		}

		return generateOtpCommon(mobileNo);
	}

	public CommonResponse generateLoginOtp(Long mobileNo) {

		String email = mobileNo + "@skillbridge.com";

		if (userRepo.findByEmail(email).isEmpty()) {
			return new CommonResponse("FAILED", "User not registered. Please signup first.", null);
		}

		return generateOtpCommon(mobileNo);
	}

	private CommonResponse generateOtpCommon(Long mobileNo) {

//		int otp = 100000 + secureRandom.nextInt(900000);
		int otp = 123456;

		WorkerLoginOtp entity = new WorkerLoginOtp();
		entity.setMobileno(mobileNo.intValue());
		entity.setOtp(String.valueOf(otp));
		entity.setVerified(false);
		entity.setAttempts(0);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setExpiresAt(LocalDateTime.now().plusMinutes(2));

		otpRepo.save(entity);

		Map<String, Object> result = new HashMap<>();
		result.put("otpSent", otp);
		result.put("otpExpiryMinutes", 2);

		return new CommonResponse("200", "OTP Sent Successfully", result);
	}

	public CommonResponse verifyOtpAndRegister(VerifyOtpRegisterRequest req) {

		String email = req.getMobileNo() + "@skillbridge.com";

		if (userRepo.findByEmail(email).isPresent()) {
			return new CommonResponse("FAILED", "User already registered. Please login.", null);
		}

		WorkerLoginOtp otpEntity = otpRepo
				.findTopByMobilenoAndVerifiedFalseOrderByCreatedAtDesc(req.getMobileNo().intValue()).orElse(null);

		if (otpEntity == null) {
			return new CommonResponse("FAILED", "OTP not generated. Please request OTP.", null);
		}

		if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
			return new CommonResponse("FAILED", "OTP Expired. Please request new OTP.", null);
		}

		if (!otpEntity.getOtp().equals(req.getOtp())) {
			return new CommonResponse("FAILED", "Invalid OTP", null);
		}

		otpEntity.setVerified(true);
		otpRepo.save(otpEntity);

		User user = new User();
		user.setName(req.getFullName());
		user.setEmail(email);
		user.setIsActive(true);

		userRepo.save(user);

		return new CommonResponse("200", "Registration Successful", null);
	}

	public CommonResponse verifyOtpAndLogin(Long mobileNo, String otp) {

		String email = mobileNo + "@skillbridge.com";

		User user = userRepo.findByEmail(email).orElse(null);

		if (user == null) {
			return new CommonResponse("FAILED", "User not registered. Please signup first.", null);
		}

		WorkerLoginOtp otpEntity = otpRepo.findTopByMobilenoAndVerifiedFalseOrderByCreatedAtDesc(mobileNo.intValue())
				.orElse(null);

		if (otpEntity == null) {
			return new CommonResponse("FAILED", "OTP not generated. Please request OTP.", null);
		}

		if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
			return new CommonResponse("FAILED", "OTP Expired. Please request new OTP.", null);
		}

		if (!otpEntity.getOtp().equals(otp)) {
			return new CommonResponse("FAILED", "Invalid OTP", null);
		}

		otpEntity.setVerified(true);
		otpRepo.save(otpEntity);

		String accessToken = jwtUtil.generateMobileWorkerToken(user.getId(), mobileNo.toString());

		String refreshToken = jwtUtil.generateRefreshToken(user.getId());

		UserToken token = new UserToken();
		token.setUserId(user.getId());
		token.setRefreshToken(refreshToken);
		token.setTokenType("REFRESH");
		token.setRevoked(false);
		token.setCreatedAt(LocalDateTime.now());
		token.setExpiresAt(LocalDateTime.now().plusDays(7));

		tokenRepo.save(token);

		Map<String, Object> result = new HashMap<>();
		result.put("accessToken", accessToken);
		result.put("refreshToken", refreshToken);

		return new CommonResponse("200", "Login Successful", result);
	}
}
