import { Component } from '@angular/core';
import { CandidateService } from '../service/CandidateService';
import { RouterLink } from '@angular/router';

@Component({
  imports: [RouterLink],
  selector: 'app-candidate-delete',
  styleUrl: './candidate-delete.css',
  templateUrl: './candidate-delete.html',
})

export class CandidateDelete {

  candidateId: string = '';

  constructor(private candidateService: CandidateService) {}

  public deleteCandidate(candidateId: string): void {
    this.candidateService.deleteCandidate(candidateId).subscribe({
      next: (response: string) => {
        console.log('Candidato deletado com sucesso!', response);
      },

      error: (error) => {
        console.error('Erro ao tentar deletar o candidato. Tente novamente.', error);
      }
    })
  }

  public confirmDelete(): void {
  const confirmed = confirm(
    'Tem certeza de que deseja excluir este candidato?'
  );

  if (confirmed) {
    this.deleteCandidate(this.candidateId);
  }
}
}
