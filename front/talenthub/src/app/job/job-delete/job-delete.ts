
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

import { JobService } from '../service/job-service';

@Component({
  selector: 'app-job-delete',
  imports: [CommonModule, RouterLink],
  templateUrl: './job-delete.html',
  styleUrl: './job-delete.css',
})
export class JobDelete implements OnInit {

  jobId: string = '';
  loading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  // Obtém o ID da vaga informado na rota.
  ngOnInit(): void {

    this.jobId = this.route.snapshot.paramMap.get('id') ?? '';

    if (!this.jobId) {
      this.errorMessage = 'ID da vaga não informado.';
    }
  }

  public deleteJob(): void {

    if (!this.jobId || this.loading) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.jobService.deleteJob(this.jobId).subscribe({

      next: () => {

        this.loading = false;

        this.router.navigate(['/vagas'], {
          queryParams: {
            mensagem: 'Vaga excluída com sucesso!'
          }
        });
      },

      error: (error) => {

        this.loading = false;

        console.error('Erro ao excluir vaga:', error);

        this.errorMessage =
          error.error?.message ??
          'Não foi possível excluir a vaga. Tente novamente.';
      }
    });
  }

  // Solicita confirmação antes de excluir.
  public confirmDelete(): void {

    if (!this.jobId || this.loading) {
      return;
    }

    const confirmed = confirm(
      'Tem certeza de que deseja excluir esta vaga? Esta ação não poderá ser desfeita.'
    );

    if (confirmed) {
      this.deleteJob();
    }
  }
}
