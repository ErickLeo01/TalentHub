import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CandidateService } from '../service/CandidateService';

@Component({
  selector: 'app-candidate-delete',
  imports: [CommonModule, RouterLink],
  templateUrl: './candidate-delete.html',
  styleUrl: './candidate-delete.css',
})
export class CandidateDelete implements OnInit {

  candidateId: string = '';
  loading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private candidateService: CandidateService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.candidateId = this.route.snapshot.paramMap.get('id') ?? '';

    if (!this.candidateId) {
      this.errorMessage = 'ID do candidato não informado.';
    }
  }

  public deleteCandidate(): void {
    if (!this.candidateId || this.loading) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.candidateService.deleteCandidate(this.candidateId).subscribe({
      next: (responseMessage) => {
        this.loading = false;
        this.successMessage = responseMessage || 'Candidato excluído com sucesso!';

        // Aguarda 1.5s antes de redirecionar para a lista atualizar
        setTimeout(() => {
          this.router.navigate(['/candidatos']);
        }, 1500);
      },

      error: (error) => {
        this.loading = false;
        console.error('Erro ao excluir candidato:', error);

        if (error.status === 404) {
          this.errorMessage = 'Candidato não encontrado no banco de dados. Ele pode ter sido excluído anteriormente.';
        } else if (error.status === 403) {
          this.errorMessage = 'Acesso negado: Você só possui permissão para excluir a sua própria conta.';
        } else {
          this.errorMessage = error.error?.message ?? 'Não foi possível excluir o candidato. Tente novamente.';
        }
      }
    });
  }

  public confirmDelete(): void {
    if (!this.candidateId || this.loading) {
      return;
    }

    const confirmed = confirm(
      'Tem certeza de que deseja excluir este candidato? Esta ação não poderá ser desfeita.'
    );

    if (confirmed) {
      this.deleteCandidate();
    }
  }
}
