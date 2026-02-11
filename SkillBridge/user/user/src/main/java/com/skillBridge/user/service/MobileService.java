package com.skillBridge.user.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

	private String normalizePhone(Long mobileNo) {
		return String.valueOf(mobileNo).trim();
	}

	private String[] splitName(String fullName) {

		if (fullName == null || fullName.trim().isEmpty())
			return new String[] { "", "" };

		String[] parts = fullName.trim().split("\\s+");

		String first = parts[0];
		String last = parts.length > 1 ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)) : "";

		return new String[] { first, last };
	}

	private String generateWorkerCode() {
		return "WRK" + System.currentTimeMillis();
	}

	public CommonResponse generateSignupOtp(Long mobileNo) {

		String phone = normalizePhone(mobileNo);

		if (workerRepo.findByPhone(phone).isPresent()) {
			return new CommonResponse("400", "User already registered. Please login.", null);
		}

		//int otp = 100000 + secureRandom.nextInt(900000);
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

		String phone = normalizePhone(req.getMobileNo());

		if (workerRepo.findByPhone(phone).isPresent()) {
			return new CommonResponse("400", "User already registered. Please login.", null);
		}

		Optional<WorkerLoginOtp> otpOpt = otpRepo
				.findTopByMobilenoAndVerifiedFalseOrderByCreatedAtDesc(req.getMobileNo().intValue());

		if (otpOpt.isEmpty())
			return new CommonResponse("400", "OTP not generated.", null);

		WorkerLoginOtp otpEntity = otpOpt.get();

		if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now()))
			return new CommonResponse("400", "OTP Expired", null);

		if (!otpEntity.getOtp().equals(req.getOtp()))
			return new CommonResponse("400", "Invalid OTP", null);

		otpEntity.setVerified(true);
		otpRepo.save(otpEntity);

		String[] names = splitName(req.getFullName());

		Worker worker = new Worker();

		worker.setWorkerCode(generateWorkerCode());

		worker.setFirstName(names[0]);
		worker.setLastName(names[1]);

		worker.setPhone(phone);
		worker.setEmail(phone + "@skillbridge.com");

		worker.setAddress(req.getAddress());
		worker.setCity(req.getCity());
		worker.setState(req.getState());

		worker.setYearsOfExperience(req.getExperience());
		worker.setAge(req.getAge() != null ? Integer.parseInt(req.getAge()) : null);
		worker.setGender(req.getGender());

		worker.setIsActive(true);
		worker.setIsAvailable(true);
		worker.setIsCompanyAssigned(false);
		worker.setIsBlocked(false);
		worker.setIsEmailVerified(false);
		worker.setIsPhoneVerified(true);
		worker.setIsAccountLocked(false);

		worker.setRating(0f);
		worker.setTotalJobsCompleted(0);
		worker.setTotalJobsCancelled(0);
		worker.setFailedLoginAttempts(0);

		worker.setLastLoginAt(LocalDateTime.now());

		workerRepo.save(worker);

		return new CommonResponse("200", "Registration Successful", null);
	}

	public CommonResponse generateLoginOtp(Long mobileNo) {

		String phone = normalizePhone(mobileNo);

		if (workerRepo.findByPhone(phone).isEmpty()) {
			return new CommonResponse("400", "User not registered. Please signup first.", null);
		}

	//	int otp = 100000 + secureRandom.nextInt(900000);
		int otp =123456;
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

		String phone = normalizePhone(mobileNo);

		Worker worker = workerRepo.findByPhone(phone).orElseThrow(() -> new RuntimeException("User not registered"));

		Optional<WorkerLoginOtp> otpOpt = otpRepo
				.findTopByMobilenoAndVerifiedFalseOrderByCreatedAtDesc(mobileNo.intValue());

		if (otpOpt.isEmpty())
			return new CommonResponse("400", "OTP not generated.", null);

		WorkerLoginOtp otpEntity = otpOpt.get();

		if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now()))
			return new CommonResponse("400", "OTP Expired", null);

		if (!otpEntity.getOtp().equals(otp))
			return new CommonResponse("400", "Invalid OTP", null);

		otpEntity.setVerified(true);
		otpRepo.save(otpEntity);

		String accessToken = jwtUtil.generateMobileWorkerToken(worker.getId(), phone);

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
