import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobDTO } from '../interface/job-dto-interface';
import { JobService } from '../service/job-service';

@Component({
  imports: [FormsModule],
  selector: 'app-job-create',
  styleUrl: './job-create.css',
  templateUrl: './job-create.html',
})

export class JobCreate {

  job: JobDTO = {
    name: '',
    description: '',
    salary: 0,
    benefits: ''
  };

  constructor(private jobService: JobService) {}

  public createJob(): void {
    this.jobService.createJob(this.job).subscribe({
      next: (response) => {
        alert('Vaga cadastrada com sucesso!');
        console.log('Vaga cadastrada com sucesso!', response);
      },

      error: (error) => {
        alert('Vaga não cadastrada. Tente novamente.');
        console.log('Vaga não cadastrada.', error);
      }
    })
  }
}
