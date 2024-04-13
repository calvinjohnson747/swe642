package com.backend.springapi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.backend.springapi.model.StudentSurvey;
//import com.backend.springapi.repository.StudentRepository;
import com.backend.springapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class StudentSurveyController {
	
	//@Autowired
	//private StudentRepository studentRepository;
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/getStudents")
	public ResponseEntity<List<StudentSurvey>> getAllStudents(){
		List<StudentSurvey> ssList = new ArrayList<>();
		ssList = studentService.getAllStudents();
		if (ssList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(ssList, HttpStatus.OK);
	}
	@GetMapping("/getStudents/{id}")
    public ResponseEntity<StudentSurvey> getStudentById(@PathVariable Long id) throws Exception {
        StudentSurvey student = studentService.getStudentById(id);
        if (student!=null) {
            return ResponseEntity.ok(student);
        } else {
            throw new Exception("Student does not exist");
        }	
    }

	@PostMapping("/saveStudents")
	public ResponseEntity<StudentSurvey> saveStudents(@RequestBody StudentSurvey studentSurvey){
		try {
			StudentSurvey ssObj = studentService.saveStudents(studentSurvey);
			return new ResponseEntity<>(ssObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

    @GetMapping("/zipcodes")
    public ResponseEntity<String> getZipCodes() {
        try {
            // Read the content of the zipcodes.json file
            Resource resource = new ClassPathResource("zipcodes.json");
            String jsonContent = new String(Files.readAllBytes(Paths.get(resource.getURI())));
            // Return the JSON content
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

	@PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentSurvey> updateStudent(@PathVariable("id") Long id, @RequestBody StudentSurvey studentSurvey) {
        try {
            StudentSurvey existingStudent = studentService.getStudentById(id);
            if (existingStudent != null) {
                StudentSurvey updatedStudent = studentService.updateStudents(studentSurvey);
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


