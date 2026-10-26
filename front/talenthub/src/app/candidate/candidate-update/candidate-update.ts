import { Component, OnInit, inject, PLATFORM_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidateUpdateDTO } from '../interface/candidate-update-dto.interface';
import { CandidateService } from '../service/CandidateService';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';
import { CommonModule, isPlatformBrowser } from '@angular/common';

@Component({
  imports: [FormsModule, CommonModule],
  selector: 'app-candidate-update',
  styleUrl: './candidate-update.css',
  templateUrl: './candidate-update.html',
})
export class CandidateUpdate implements OnInit {

  private platformId = inject(PLATFORM_ID);

  candidateId: string = '';
  loading: boolean = true;
  saving: boolean = false;
  message: string = '';
  errorMessage: string = '';

  candidate: CandidateUpdateDTO = {
    name: '',
    email: '',
    password: '',
    cpf: '',
    description: ''
  };

  constructor(
    private candidateService: CandidateService,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit(): void {
    // Escuta as alterações na rota para capturar o parâmetro 'id'
    this.route.paramMap.subscribe(params => {
      this.candidateId = params.get('id') ?? '';

      if (!this.candidateId) {
        this.errorMessage = 'ID do candidato não informado na URL.';
        this.loading = false;
        return;
      }

      this.findCandidateById();
    });
  }

  public goBack(): void {
    this.router.navigate(['/candidatos']);
  }

  public findCandidateById(): void {
    if (!this.candidateId) return;

    this.loading = true;
    this.errorMessage = '';

    this.candidateService.findCandidateById(this.candidateId).subscribe({
      next: (response: CandidateResponseDTO) => {
        this.candidate.name = response.name;
        this.candidate.email = response.email;
        this.candidate.description = response.description;

        this.loading = false; // Oculta a mensagem de carregamento
      },
      error: (error) => {
        console.error('Erro ao buscar dados do candidato:', error);
        this.errorMessage = 'Não foi possível carregar os dados do candidato.';
        this.loading = false; // Desativa o carregamento mesmo em caso de erro
      }
    });
  }

  public updateCandidate(): void {
    if (this.saving || this.loading || !this.candidateId) {
      return;
    }

    this.message = '';
    this.errorMessage = '';

    const candidateUpdate: CandidateUpdateDTO = {};

    if (this.candidate.name?.trim()) {
      candidateUpdate.name = this.candidate.name.trim();
    }
    if (this.candidate.email?.trim()) {
      candidateUpdate.email = this.candidate.email.trim();
    }
    if (this.candidate.password?.trim()) {
      candidateUpdate.password = this.candidate.password;
    }
    if (this.candidate.cpf?.trim()) {
      candidateUpdate.cpf = this.candidate.cpf.trim();
    }
    if (this.candidate.description !== undefined && this.candidate.description !== null) {
      candidateUpdate.description = this.candidate.description;
    }

    this.saving = true;

    this.candidateService.updateCandidate(
      this.candidateId,
      candidateUpdate
    ).subscribe({
      next: () => {
        this.saving = false;
        this.message = 'Cadastro atualizado com sucesso!';
        this.router.navigate(['/candidatos']);
      },
      error: (error) => {
        this.saving = false;
        console.error('Erro ao atualizar candidato:', error);
        this.errorMessage =
          error.error?.message ??
          'Não foi possível atualizar o cadastro. Verifique os dados e tente novamente.';
      }
    });
  }
}
