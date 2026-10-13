import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CandidateService } from '../service/CandidateService';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';

@Component({
  selector: 'app-candidate-profile',
  imports: [CommonModule, RouterLink],
  templateUrl: './candidate-profile.html',
  styleUrl: './candidate-profile.css',
})
export class CandidateProfile implements OnInit {

  candidate: CandidateResponseDTO | null = null;

  loading: boolean = false;
  errorMessage: string = '';

  constructor(
    private candidateService: CandidateService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.findCandidateProfile();
  }

  // Busca o perfil do candidato autenticado no backend.
  public findCandidateProfile(): void {
    this.loading = true;
    this.errorMessage = '';

    this.candidateService.findMyProfile().subscribe({
      next: (response: CandidateResponseDTO) => {
        this.candidate = response;
        this.loading = false;
        console.log('Perfil do candidato carregado com sucesso.');
      },

      error: (error: any) => {
        console.error('Erro ao buscar perfil do candidato:', error);
        this.errorMessage =
          'Não foi possível buscar o perfil do candidato.';
        this.loading = false;
      }
    });
  }

  // Abre a página de atualização do próprio cadastro.
  public updateCandidate(): void {
    if (!this.candidate || this.loading) {
      return;
    }

    this.router.navigate([
      '/candidatos/update',
      this.candidate.id
    ]);
  }
}
