import { Component } from '@angular/core';
import { FormsModule }
import { JobDTO } from '../interface/job-dto-interface';
import { JobService } from '../service/job-service';

@Component({
  imports: [FormsModule],
  selector: 'app-job-update',
  styleUrl: './job-update.css',
  templateUrl: './job-update.html',
})
export class JobUpdate {}
