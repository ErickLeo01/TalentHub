
import { Component, OnInit } from '@angular/core';
import {
  CommonModule,
  CurrencyPipe
} from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { JobService } from '../service/job-service';
import { JobResponseDTO } from '../interface/job-response.interface';

@Component({
  selector: 'app-job-details',
  imports: [CommonModule, CurrencyPipe],
  templateUrl: './job-details.html',
  styleUrl: './job-details.css',
})
export class JobDetails implements OnInit {

  jobId: string = '';
  loading: boolean = true;
  errorMessage: string = '';

  job: JobResponseDTO | null = null;

  constructor(
    private jobService: JobService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.jobId = this.route.snapshot.paramMap.get('id') ?? '';

    if (!this.jobId) {
      this.errorMessage = 'ID da vaga não informado.';
      this.loading = false;
      return;
    }

    this.findJobById();
  }

  public findJobById(): void {

    this.loading = true;
    this.errorMessage = '';

    this.jobService.findJobById(this.jobId).subscribe({

      next: (response: JobResponseDTO) => {

        this.job = response;
        this.loading = false;

      },

      error: () => {

        this.errorMessage = 'Não foi possível carregar os dados da vaga.';
        this.loading = false;

      }

    });
  }
}
