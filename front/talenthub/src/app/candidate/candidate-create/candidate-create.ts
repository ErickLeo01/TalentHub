import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CandidateDTO } from '../interface/candidate-dto.interface';
import { CandidateService } from '../service/CandidateService';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';

@Component({
  selector: 'app-candidate-create',
  imports: [FormsModule],
  templateUrl: './candidate-create.html',
  styleUrl: './candidate-create.css',
})
export class CandidateCreate {
  candidate: CandidateDTO = {
    name: '',
    email: '',
    password: '',
    cpf: '',
    description: ''
  }

  constructor(private candidateService: CandidateService) {}

  public createCandidate(): void {
    this.candidateService.createCandidate(this.candidate).subscribe({
      next: (response: CandidateResponseDTO) => {
        console.log('Candidato criado com sucesso!', response);

        this.candidate = {
          name: '',
          email: '',
          password: '',
          cpf: '',
          description: ''
        };
      },
      error: (error) => {
        console.error('Erro ao tentar criar o candidato. Tente novamente', error);
      }

    });
  }
}
