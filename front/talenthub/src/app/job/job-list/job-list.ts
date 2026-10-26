
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
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
    private jobService: JobService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const name = params.get('name');

      if (name) {
        this.findJobsByName(name);
      } else {
        this.findJobs();
      }
    });
  }

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

  public findJobsByName(name: string): void {
    this.loading = true;
    this.errorMessage = '';

    this.jobService.findJobByName(name).subscribe({
      next: (response) => {
        this.jobs = response;
        this.loading = false;

        if (response.length === 0) {
          this.errorMessage = 'Nenhuma vaga encontrada com esse nome.';
        }
      },
      error: (error) => {
        console.error('Erro ao buscar vaga:', error);
        this.errorMessage = 'Não foi possível buscar a vaga.';
        this.loading = false;
      }
    });
  }
}
