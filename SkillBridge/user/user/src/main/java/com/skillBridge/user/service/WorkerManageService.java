package com.skillBridge.user.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.skillBridge.user.dto.CommonResponse;
import com.skillBridge.user.dto.workerSearch.WorkerSearchRequest;
import com.skillBridge.user.dto.workerSearch.WorkerSearchResponse;
import com.skillBridge.user.model.master.Worker;
import com.skillBridge.user.repository.WorkerRepository;

@Service
public class WorkerManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerManageService.class);
	
	private final WorkerRepository workerRepository;
	
	public WorkerManageService(WorkerRepository workerRepository) {
		this.workerRepository = workerRepository;
	}
	
	public CommonResponse searchWorkers(WorkerSearchRequest req) {

        CommonResponse response = new CommonResponse();

        try {
        	// -------- Validation --------
            if (req == null) {
                response.setStatus("400");
                response.setMessage("Request cannot be null");
                return response;
            }

            if (req.getPage() == null || req.getPage() < 0) {
                response.setStatus("400");
                response.setMessage("Page must be >= 0");
                return response;
            }

            if (req.getSize() == null || req.getSize() <= 0 || req.getSize() > 200) {
                response.setStatus("400");
                response.setMessage("Size must be between 1 and 200");
                return response;
            }

         // -------- Determine Sorting --------
            Sort sort;

            boolean noFilters =
                    req.getCategoryId() == null &&
                    req.getSkillId() == null &&
                    (req.getSortBy() == null || req.getSortBy().isBlank());

            if (noFilters) {
                // Default sort when only pagination used
                sort = Sort.by(
                        Sort.Order.desc("rating"),
                        Sort.Order.desc("yearsOfExperience")
                );
            } else {

                String sortBy =
                        Optional.ofNullable(req.getSortBy())
                                .filter(s -> !s.isBlank())
                                .orElse("rating");

                String direction =
                        Optional.ofNullable(req.getDirection())
                                .filter(s -> !s.isBlank())
                                .orElse("DESC");

                sort = Sort.by(
                        Sort.Direction.fromString(direction),
                        sortBy
                );
            }

            Pageable pageable =
                    PageRequest.of(req.getPage(), req.getSize(), sort);
 

            Page<Worker> page =
                    workerRepository.searchWorkers(
                            req.getCategoryId(),
                            req.getSkillId(),
                            pageable);

            List<WorkerSearchResponse> result =
                    page.getContent()
                        .stream()
                        .map(w -> new WorkerSearchResponse(
                                w.getId(),
                                w.getWorkerCode(),
                                w.getFirstName(),
                                w.getLastName(),
                                w.getRating(),
                                w.getYearsOfExperience()))
                        .toList();

            response.setStatus("200");
            response.setMessage("Workers fetched");
            response.setResult(result);

        } catch (Exception ex) {
            response.setStatus("500");
            response.setMessage("Internal error");
        }

        return response;
    }
}
