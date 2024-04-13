import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-survey-form',
  templateUrl: './survey-form.component.html',
  styleUrl: './survey-form.component.css',
})

//This typescript is resposible for rendering the SurveyForm Componenet and it all validates all the input given by the user
//from the survey form
export class SurveyFormComponent {
  private submitUrl = 'http://localhost:8080/saveStudents';
  surveyForm!: FormGroup;
  zipData: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient
  ) {}

  ngOnInit() {
    this.surveyForm = this.formBuilder.group({
      firstName: ['', Validators.pattern('^[a-zA-Z ]*$')],
      lastName: ['', Validators.pattern('^[a-zA-Z ]*$')],
      email: ['', [Validators.required, Validators.email]],
      telephoneNumber: ['', Validators.required],
      streetAddress: ['', Validators.pattern('^[a-zA-Z0-9\\s]*$')],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zip: ['', [Validators.required, Validators.maxLength(5)]],
      surveyDate: ['', Validators.required],
      liked: this.formBuilder.group({
        students: false,
        location: false,
        campus: false,
        atmosphere: false,
        dormRooms: false,
        sports: false,
      }),
      interest: ['', Validators.required],
      comments: ['', Validators.required],
      recommend: ['', Validators.required],
    });

   }
  

  onSubmit() {
    if (this.surveyForm && this.surveyForm.valid) {
      console.log(this.surveyForm.value);
      this.httpClient.post(this.submitUrl, this.surveyForm.value).subscribe({
        next: (response) => alert("Form Submitted Successfully"),
        error: (error) => console.error('Error submitting survey', error),
      });
    } else {
       // Validation checks
       const errors = [];
  
       // Check username for alphabets only
       const firstNameControl = this.surveyForm?.get('firstName');
       if (firstNameControl && !/^[a-zA-Z ]*$/.test(firstNameControl.value)) {
         errors.push("First Name should contain only alphabets.");
         // Clear the field with error
         firstNameControl.setValue('')
       }
   
       const lastNameControl = this.surveyForm?.get('lastName');
       if (lastNameControl && !/^[a-zA-Z ]*$/.test(lastNameControl.value)) {
         errors.push("Last Name should contain only alphabets.");
         // Clear the field with error
         lastNameControl.setValue('');
       }
   
       // Check email format
       const emailControl = this.surveyForm?.get('email');
       if (emailControl && !emailControl.valid) {
         errors.push("Invalid email format.");
         // Clear the field with error
          emailControl.setValue('');
 
       }
   
       // Check if the street address contains only appropriate characters
       const streetAddressControl = this.surveyForm?.get('streetAddress');
       if (streetAddressControl && !/^[a-zA-Z0-9\s]*$/.test(streetAddressControl.value)) {
         errors.push("Street address should contain only appropriate characters (numeric, alphabet or alphanumeric characters).");
         // Clear the field with error
         streetAddressControl.setValue('');
       }
   
       // Check at least two checkboxes are checked
       const likedControl = this.surveyForm?.get('liked');
       if (likedControl) {
         const likedCheckboxes = likedControl.value;
         const checkedCount = Object.values(likedCheckboxes).filter(checked => checked).length;
         if (checkedCount < 2) {
           errors.push("Select at least two options from 'What did you like most about the campus?'");
           // Clear the field with error
           likedControl.setValue({
             students: false,
             location: false,
             campus: false,
             atmosphere: false,
             dormRooms: false,
             sports: false,
           });
         }
       } else {
         errors.push("Liked options are not available.");
       }
   
       // Check if a radio button option is selected
       const interestControl = this.surveyForm?.get('interest');
       if (!interestControl || !interestControl.value) {
         errors.push("Select an option for 'How did you become interested in the university?'");}
  
          // Display consolidated error message if any
    if (errors.length > 0) {
      window.alert("Please correct the following errors:\n\n" + errors.join("\n"));
    } else {
      console.error('Form is invalid');
    }
  }
} 
  
  resetForm(){
    this.surveyForm.reset();
  }
}
function fetchZipData(zipCode: any, string: any) {
  throw new Error('Function not implemented.');
}

function updateCityAndState(zipCode: any, string: any) {
  throw new Error('Function not implemented.');
}

