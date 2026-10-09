import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CandidateDTO } from '../interface/candidate-dto.interface';
import { CandidateService } from '../service/CandidateService';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';


@Component({
  imports: [FormsModule],
  selector: 'app-candidate-update',
  styleUrl: './candidate-update.css',
  templateUrl: './candidate-update.html',
})

export class CandidateUpdate {

  candidateId: string = '';

  candidate: CandidateDTO = {
    name: '',
    email: '',
    password: '',
    cpf: '',
    description: ''
  };

  constructor(private candidateService: CandidateService) {}

  public updateCandidate(): void {
    this.candidateService.updateCandidate(this.candidateId, this.candidate).subscribe({
      next: (response: CandidateResponseDTO) => {
        console.log('Candidato atualizado com sucesso!', response);
      },
      error: (error) => {
        console.error('Erro ao tentar atualizar o candidato. Tente novamente.', error);
      }
    });
  }
}
