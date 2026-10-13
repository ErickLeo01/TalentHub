import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CandidateService } from '../service/CandidateService';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';

@Component({
  imports: [CommonModule],
  selector: 'app-candidate-list',
  styleUrl: './candidate-list.css',
  templateUrl: './candidate-list.html',
})
export class CandidateList implements OnInit {

  candidates: CandidateResponseDTO[] = [];

  loading: boolean = false;
  errorMessage: string = '';

  constructor(private candidateService: CandidateService,private router: Router) {}

  ngOnInit(): void {
    this.findCandidates();
  }

  public findCandidates(): void {
    this.loading = true;
    this.errorMessage = '';

    this.candidateService.findCandidateByName('').subscribe({
      next: (response: CandidateResponseDTO[]) => {
        this.candidates = response;
        this.loading = false;
      },
      error: (error) => {
        console.error('Erro ao buscar candidatos.', error);

        this.errorMessage =
          'Não foi possível carregar a lista de candidatos.';

        this.loading = false;
      }
    });
  }

  public updateCandidate(candidate: CandidateResponseDTO): void {
    this.router.navigate(['/candidatos/update', candidate.id]);
  }
}
