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

  // Obtém o ID do candidato informado na rota.
  ngOnInit(): void {
    this.candidateId = this.route.snapshot.paramMap.get('id') ?? '';

    if (!this.candidateId) {
      this.errorMessage = 'ID do candidato não informado.';
    }
  }

  // Solicita a exclusão do candidato.
  public deleteCandidate(): void {
    if (!this.candidateId || this.loading) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.candidateService.deleteCandidate(this.candidateId).subscribe({
      next: () => {
        this.loading = false;
        this.successMessage = 'Candidato excluído com sucesso!';

        // Retorna para a lista após a exclusão.
        this.router.navigate(['/candidatos']);
      },

      error: (error) => {
        this.loading = false;

        console.error('Erro ao excluir candidato:', error);

        this.errorMessage =
          error.error?.message ??
          'Não foi possível excluir o candidato. Tente novamente.';
      }
    });
  }

  // Solicita confirmação antes de excluir.
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
