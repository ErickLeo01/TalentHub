import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidateUpdateDTO } from '../interface/candidate-update-dto.interface';
import { CandidateService } from '../service/CandidateService';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';
import { CommonModule } from '@angular/common';

@Component({
  imports: [FormsModule, CommonModule],
  selector: 'app-candidate-update',
  styleUrl: './candidate-update.css',
  templateUrl: './candidate-update.html',
})
export class CandidateUpdate implements OnInit {

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
    private router: Router
  ) {}

  ngOnInit(): void {
    this.candidateId = this.route.snapshot.paramMap.get('id') ?? '';

    if (!this.candidateId) {
      this.errorMessage = 'ID do candidato não informado.';
      this.loading = false;
      return;
    }

    this.findCandidateById();
  }

  public findCandidateById(): void {
    this.loading = true;
    this.errorMessage = '';

    this.candidateService.findCandidateById(this.candidateId).subscribe({
      next: (response: CandidateResponseDTO) => {
        this.candidate.name = response.name;
        this.candidate.email = response.email;
        this.candidate.description = response.description;

        this.loading = false;
      },
      error: (error) => {
        console.error('Erro ao buscar candidato.', error);
        this.errorMessage = 'Não foi possível carregar os dados do candidato.';
        this.loading = false;
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

    if (this.candidate.description !== undefined &&
        this.candidate.description !== null) {
      candidateUpdate.description = this.candidate.description;
    }

    this.saving = true;

    this.candidateService.updateCandidate(
      this.candidateId,
      candidateUpdate
    ).subscribe({
      next: (response: CandidateResponseDTO) => {
        this.saving = false;
        this.message = 'Cadastro atualizado com sucesso!';

        console.log('Candidato atualizado:', response);
        this.router.navigate(['/candidatos']);
      },
      error: (error) => {
        this.saving = false;
        console.error('Erro ao atualizar candidato.', error);

        this.errorMessage =
          error.error?.message ??
          'Não foi possível atualizar o cadastro. Verifique os dados e tente novamente.';
      }
    });
  }
}
