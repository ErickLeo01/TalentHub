import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CandidateCreate } from './candidate/candidate-create/candidate-create';
import { CandidateDelete } from './candidate/candidate-delete/candidate-delete';
import { CandidateUpdate } from './candidate/candidate-update/candidate-update';
import { CandidateList } from './candidate/candidate-list/candidate-list';
import { CandidateLogin } from './candidate/candidate-login/candidate-login';
import { CandidateProfile } from './candidate/candidate-profile/candidate-profile';
import { CompanyCreate } from './company/company-create/company-create';
import { CompanyUpdate } from './company/company-update/company-update';
import { CompanyDelete } from './company/company-delete/company-delete';
import { CompanyList } from './company/company-list/company-list';
import { CompanyProfile } from './company/company-profile/company-profile';
import { CompanyLogin } from './company/company-login/company-login';
import { JobCreate } from './job/job-create/job-create';
import { JobUpdate } from './job/job-update/job-update';
import { JobDelete } from './job/job-delete/job-delete';
import { JobDetails } from './job/job-details/job-details';
import { JobList } from './job/job-list/job-list';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CandidateCreate, CandidateDelete, CandidateUpdate, CandidateList, CandidateLogin, CandidateProfile, CompanyCreate, CompanyUpdate, CompanyDelete, CompanyList, CompanyProfile, CompanyLogin, JobCreate, JobUpdate, JobDelete, JobDetails, JobList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('talenthub');
}
