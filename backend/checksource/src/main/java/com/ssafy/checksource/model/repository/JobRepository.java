package com.ssafy.checksource.model.repository;

import com.ssafy.checksource.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long>{

    Job findByJobId(Long jobId);

}
