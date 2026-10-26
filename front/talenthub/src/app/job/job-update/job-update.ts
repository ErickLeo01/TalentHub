import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { JobUpdateDTO } from '../interface/job-update-dto.interface';
import { JobService } from '../service/job-service';
import { JobResponseDTO } from '../interface/job-response.interface';

@Component({
  selector: 'app-job-update',
  imports: [FormsModule, CommonModule],
  templateUrl: './job-update.html',
  styleUrl: './job-update.css',
})
export class JobUpdate implements OnInit {

  jobId: string = '';
  jobName: string = '';

  loading: boolean = true;
  saving: boolean = false;
  message: string = '';
  errorMessage: string = '';

  job: JobUpdateDTO = {
    name: '',
    description: '',
    salary: 0,
    benefits: ''
  };

  constructor(
    private jobService: JobService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.jobName = this.route.snapshot.paramMap.get('name') ?? '';

    if (!this.jobName) {
      this.errorMessage = 'Nome da vaga não informado.';
      this.loading = false;
      return;
    }

    this.findJobByName();
  }

  public findJobByName(): void {

    this.loading = true;
    this.errorMessage = '';

    this.jobService.findJobByName(this.jobName).subscribe({

      next: (response: JobResponseDTO[]) => {

        const foundJob = response.find(
          (job) => job.name === this.jobName
        );

        if (!foundJob) {
          this.errorMessage = 'Vaga não encontrada.';
          this.loading = false;
          return;
        }

        this.jobId = foundJob.id;
        this.job.name = foundJob.name;
        this.job.description = foundJob.description;
        this.job.salary = foundJob.salary;
        this.job.benefits = foundJob.benefits;

        this.loading = false;
      },

      error: () => {
        this.errorMessage = 'Não foi possível carregar os dados da vaga.';
        this.loading = false;
      }

    });
  }

  public updateJob(): void {

    if (this.saving || !this.jobId) {
      return;
    }

    this.saving = true;
    this.message = '';
    this.errorMessage = '';

    this.jobService.updateJob(this.jobId, this.job).subscribe({

      next: () => {
        this.message = 'Vaga atualizada com sucesso!';
        this.saving = false;
      },

      error: () => {
        this.errorMessage = 'Não foi possível atualizar a vaga.';
        this.saving = false;
      }

    });
  }
}
