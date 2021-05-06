package com.ssafy.checksource.model.repository;

import com.ssafy.checksource.model.entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartRepository extends JpaRepository<Depart, Long>{

    Depart findByDepartId(Long departId);

}
