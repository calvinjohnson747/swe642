import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

interface Liked {
  students: boolean;
  location: boolean;
  campus: boolean;
  atmosphere: boolean;
  dormRooms: boolean;
  sports: boolean;
}

export interface StudentSurvey {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  telephoneNumber: string;
  streetAddress: string;
  city: string;
  state: string;
  zip: string;
  surveyDate: string; // or Date, depending on how you handle dates
  liked: Liked;
  interest: string;
  comments: string;
  recommend: string;
}

@Component({
  selector: 'app-survey-list',
  templateUrl: './survey-list.component.html',
  styleUrl: './survey-list.component.css',
})
export class SurveyListComponent {
  surveyStudents: StudentSurvey[] = [];
  constructor(private httpClient: HttpClient,
    private router: Router) {}

  ngOnInit() {
    this.fetchSurveyStudents();
  }

  fetchSurveyStudents(): void {
    this.httpClient
      .get<StudentSurvey[]>('http://localhost:8080/getStudents')
      .subscribe({
        next: (surveys) => (this.surveyStudents = surveys),
        error: (error) => console.error('Error fetching surveys:', error),
      });
  }

  deleteStudent(id: number): void {
    if (confirm('Are you sure you want to delete this student?')) {
      this.httpClient
        .delete(`http://localhost:8080/deleteStudent/${id}`)
        .subscribe({
          next: () => {
            // Filter out the deleted student from the array
            this.surveyStudents = this.surveyStudents.filter(student => student.id !== id);
            console.log('Student deleted successfully');
          },
          error: (error) => console.error('Error deleting student:', error),
        });
    }
  }

  updateStudent(id : number){
    this.router.navigate(['updatestudent',id]);

  }
}
