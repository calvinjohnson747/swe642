import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SurveyFormComponent } from './survey-form/survey-form.component';
import { SurveyListComponent } from './survey-list/survey-list.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UpdateStudentComponent } from './update-student/update-student.component';

const routes: Routes = [
  { path: 'survey', component: SurveyFormComponent },
  { path: 'list', component: SurveyListComponent },
  { path: '', component: HomePageComponent },
  {path:'updatestudent/:id', component: UpdateStudentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
