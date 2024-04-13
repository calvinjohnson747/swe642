package com.backend.springapi.repository;

import com.backend.springapi.model.StudentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository interface extends the JPA Repository which holds all the methods used to interact with the database
@Repository
public interface StudentRepository extends JpaRepository<StudentSurvey, Long>{

}
