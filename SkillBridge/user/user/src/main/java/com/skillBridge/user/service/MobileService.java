package com.skillBridge.user.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.VerifyOtpRegisterRequest;
import com.skillBridge.user.model.UserToken;
import com.skillBridge.user.model.WorkerLoginOtp;
import com.skillBridge.user.model.master.Worker;
import com.skillBridge.user.repository.UserTokenRepository;
import com.skillBridge.user.repository.WorkerLoginOtpRepository;
import com.skillBridge.user.repository.WorkerRepository;
import com.skillBridge.user.utils.JwtUtil;

@Service
public class MobileService {

	@Autowired
	private WorkerLoginOtpRepository otpRepo;

	@Autowired
	private WorkerRepository workerRepo;

	@Autowired
	private UserTokenRepository tokenRepo;

	@Autowired
	private JwtUtil jwtUtil;

	private static final SecureRandom secureRandom = new SecureRandom();

	private String[] splitName(String fullName) {

		if (fullName == null || fullName.trim().isEmpty())
			return new String[] { "", "" };

		String[] parts = fullName.trim().split("\\s+");

		String first = parts[0];
		String last = parts.length > 1 ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)) : "";

		return new String[] { first, last };
	}

	public CommonResponse generateSignupOtp(Long mobileNo) {

		if (workerRepo.findByPhone(mobileNo.toString()).isPresent()) {
			return new CommonResponse("400", "User already registered. Please login.", null);
		}

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

		if (workerRepo.findByPhone(req.getMobileNo().toString()).isPresent()) {
			return new CommonResponse("400", "User already registered. Please login.", null);
		}

		WorkerLoginOtp otpEntity = otpRepo
				.findTopByMobilenoAndVerifiedFalseOrderByCreatedAtDesc(req.getMobileNo().intValue()).orElse(null);

		if (otpEntity == null)
			return new CommonResponse("400", "OTP not generated.", null);

		if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now()))
			return new CommonResponse("400", "OTP Expired", null);

		if (!otpEntity.getOtp().equals(req.getOtp()))
			return new CommonResponse("400", "Invalid OTP", null);

		otpEntity.setVerified(true);
		otpRepo.save(otpEntity);

		String[] names = splitName(req.getFullName());

		Worker worker = new Worker();
		worker.setFirstName(names[0]);
		worker.setLastName(names[1]);
		worker.setPhone(req.getMobileNo().toString());
		worker.setEmail(req.getMobileNo() + "@skillbridge.com");
		worker.setAddress(req.getAddress());
		worker.setCity(req.getCity());
		worker.setYearsOfExperience(req.getExperience());
		worker.setIsActive(true);
		worker.setIsPhoneVerified(true);
		worker.setIsAvailable(true);
		worker.setRating(0f);
		worker.setTotalJobsCompleted(0);
		worker.setTotalJobsCancelled(0);

		workerRepo.save(worker);

		return new CommonResponse("200", "Registration Successful", null);
	}

	public CommonResponse generateLoginOtp(Long mobileNo) {

		if (workerRepo.findByPhone(mobileNo.toString()).isEmpty()) {
			return new CommonResponse("400", "User not registered. Please signup first.", null);
		}

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

	public CommonResponse verifyOtpAndLogin(Long mobileNo, String otp) {

		Worker worker = workerRepo.findByPhone(mobileNo.toString())
				.orElseThrow(() -> new RuntimeException("User not registered"));

		WorkerLoginOtp otpEntity = otpRepo.findTopByMobilenoAndVerifiedFalseOrderByCreatedAtDesc(mobileNo.intValue())
				.orElse(null);

		if (otpEntity == null)
			return new CommonResponse("400", "OTP not generated.", null);

		if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now()))
			return new CommonResponse("400", "OTP Expired", null);

		if (!otpEntity.getOtp().equals(otp))
			return new CommonResponse("400", "Invalid OTP", null);

		otpEntity.setVerified(true);
		otpRepo.save(otpEntity);

		String accessToken = jwtUtil.generateMobileWorkerToken(worker.getId(), mobileNo.toString());

		String refreshToken = jwtUtil.generateRefreshToken(worker.getId());

		UserToken tokenEntity = new UserToken();
		tokenEntity.setUserId(worker.getId());
		tokenEntity.setRefreshToken(refreshToken);
		tokenEntity.setTokenType("REFRESH");
		tokenEntity.setRevoked(false);
		tokenEntity.setCreatedAt(LocalDateTime.now());
		tokenEntity.setExpiresAt(LocalDateTime.now().plusDays(7));

		tokenRepo.save(tokenEntity);

		Map<String, Object> result = new HashMap<>();
		result.put("accessToken", accessToken);
		result.put("refreshToken", refreshToken);

		return new CommonResponse("200", "Login Successful", result);
	}

}