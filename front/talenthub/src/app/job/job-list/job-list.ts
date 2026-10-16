import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { JobService } from '../service/job-service';
import { JobResponseDTO } from '../interface/job-response.interface';

@Component({
  imports: [CommonModule, RouterLink],
  selector: 'app-job-list',
  styleUrl: './job-list.css',
  templateUrl: './job-list.html',
})
export class JobList implements OnInit {

  jobs: JobResponseDTO[] = [];
  loading: boolean = false;
  errorMessage: string = '';

  constructor(
    private jobService: JobService
  ) {}

  ngOnInit(): void {
    this.findJobs();
  }

  // Busca todas as vagas usando uma pesquisa vazia.
  public findJobs(): void {
    this.loading = true;
    this.errorMessage = '';

    this.jobService.findJobByName('').subscribe({
      next: (response) => {
        this.jobs = response;
        this.loading = false;
      },

      error: (error) => {
        console.error('Erro ao buscar vagas:', error);
        this.errorMessage = 'Não foi possível buscar as vagas.';
        this.loading = false;
      }
    });
  }
}
