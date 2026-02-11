package com.skillBridge.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.workerSearch.WorkerSearchRequest;
import com.skillBridge.user.service.WorkerManageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/master/workers")
public class WorkerManageController {
	
	private final WorkerManageService workerManageService;
	public WorkerManageController(WorkerManageService workerManageService) {
		this.workerManageService = workerManageService;
	}
	@PostMapping("/search")
    public CommonResponse search(
    		@Valid @RequestBody WorkerSearchRequest req) {

        return workerManageService.searchWorkers(req);
    }
}
