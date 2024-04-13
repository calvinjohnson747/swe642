package com.backend.springapi.service;
import com.backend.springapi.model.StudentSurvey;
import com.backend.springapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//The service class holds the businsess logic of our application
//Uses the StudentRepository object to call the methods in JPA Repository to perform CRUD operations
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    public StudentSurvey saveStudents(StudentSurvey studentSurvey) {
        StudentSurvey studentSurvey1 = new StudentSurvey();
        studentSurvey1.setCity(studentSurvey.getCity());
        System.out.println("Received Liked: " + studentSurvey.getLiked());
        studentSurvey1.setLiked(studentSurvey.getLiked());
        studentSurvey1.setComments(studentSurvey.getComments());
        studentSurvey1.setInterest(studentSurvey.getInterest());
        studentSurvey1.setEmail(studentSurvey.getEmail());
        studentSurvey1.setState(studentSurvey.getState());
        studentSurvey1.setSurveyDate(studentSurvey.getSurveyDate());
        studentSurvey1.setStreetAddress(studentSurvey.getStreetAddress());
        studentSurvey1.setRecommend(studentSurvey.getRecommend());
        studentSurvey1.setFirstName(studentSurvey.getFirstName());
        studentSurvey1.setLastName(studentSurvey.getLastName());
        studentSurvey1.setTelephoneNumber(studentSurvey.getTelephoneNumber());
        studentSurvey1.setZip(studentSurvey.getZip());

        return studentRepository.save(studentSurvey1);
    }

    public StudentSurvey updateStudents(StudentSurvey studentSurvey) {
        //StudentSurvey studentSurvey1 = new StudentSurvey();
        studentSurvey.setCity(studentSurvey.getCity());
        System.out.println("Received Liked: " + studentSurvey.getLiked());
        studentSurvey.setLiked(studentSurvey.getLiked());
        studentSurvey.setComments(studentSurvey.getComments());
        studentSurvey.setInterest(studentSurvey.getInterest());
        studentSurvey.setEmail(studentSurvey.getEmail());
        studentSurvey.setState(studentSurvey.getState());
        studentSurvey.setSurveyDate(studentSurvey.getSurveyDate());
        studentSurvey.setStreetAddress(studentSurvey.getStreetAddress());
        studentSurvey.setRecommend(studentSurvey.getRecommend());
        studentSurvey.setFirstName(studentSurvey.getFirstName());
        studentSurvey.setLastName(studentSurvey.getLastName());
        studentSurvey.setTelephoneNumber(studentSurvey.getTelephoneNumber());
        studentSurvey.setZip(studentSurvey.getZip());

        return studentRepository.save(studentSurvey);
    }

    public List<StudentSurvey> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentSurvey getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(null);
    }

    @SuppressWarnings("unchecked")
    public List<StudentSurvey> getById(Long id){
        return (List<StudentSurvey>) studentRepository.findById(id).orElseThrow(null);
    }
}
