package com.backend.springapi.model;



import java.time.LocalDate;
//import java.util.List;

import jakarta.persistence.*;
import lombok.*;

//Model Class maps all the variables in it to our sql database columns

@Entity
@Table(name="StudentSurvey")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StudentSurvey {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephoneNumber;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private LocalDate surveyDate;
	@Embedded
	private Liked liked;
	private String interest;
	private String comments;
	private String recommend;


}
