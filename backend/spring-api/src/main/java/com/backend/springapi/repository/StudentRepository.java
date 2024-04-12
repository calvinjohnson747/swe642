package com.backend.springapi.repository;

import com.backend.springapi.model.StudentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<StudentSurvey, Long>{

}
