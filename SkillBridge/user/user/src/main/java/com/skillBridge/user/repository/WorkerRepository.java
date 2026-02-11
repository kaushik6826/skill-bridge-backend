package com.skillBridge.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillBridge.user.model.master.Worker;


@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> 
{

//	@Query("""
//		    SELECT DISTINCT w
//		    FROM Worker w
//		    JOIN WorkerSkill ws ON ws.worker = w
//		    JOIN Skill s ON ws.skill = s
//		    JOIN SkillCategory c ON s.category = c
//		    WHERE (:categoryId IS NULL OR c.id = :categoryId)
//		      AND (:skillId IS NULL OR s.id = :skillId)
//		      AND w.isActive = true
//		    """)
//		    Page<Worker> searchWorkers(
//		        @Param("categoryId") Long categoryId,
//		        @Param("skillId") Long skillId,
//		        Pageable pageable);
	
	@Query("""
			SELECT DISTINCT w
			FROM Worker w
			LEFT JOIN WorkerSkill ws ON ws.worker = w
			LEFT JOIN Skill s ON ws.skill = s
			LEFT JOIN SkillCategory c ON s.category = c
			WHERE w.isActive = true
			AND (:categoryId IS NULL OR c.id = :categoryId)
			AND (:skillId IS NULL OR s.id = :skillId)
			""")
			Page<Worker> searchWorkers(
			    Long categoryId,
			    Long skillId,
			    Pageable pageable);


}
