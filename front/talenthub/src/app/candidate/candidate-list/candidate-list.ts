import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidateService } from '../service/CandidateService';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule],
  selector: 'app-candidate-list',
  styleUrl: './candidate-list.css',
  templateUrl: './candidate-list.html',
})
export class CandidateList implements OnInit {

  candidates: CandidateResponseDTO[] = [];
  searchTerm: string = '';
  loading: boolean = false;
  errorMessage: string = '';

  constructor(
    private candidateService: CandidateService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

ngOnInit(): void {
  this.route.queryParams.subscribe((queryParams) => {
    this.searchTerm = queryParams['name'] || '';
    this.findCandidates(this.searchTerm); // Executa a busca no backend
  });
}

  public search(): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: { name: this.searchTerm || null },
      queryParamsHandling: 'merge'
    });
  }

  public clearSearch(): void {
    this.searchTerm = '';
    this.search();
  }

  public findCandidates(name: string): void {
    this.loading = true;
    this.errorMessage = '';

    this.candidateService.findCandidateByName(name).subscribe({
      next: (response: CandidateResponseDTO[]) => {
        this.candidates = response;
        this.loading = false;
      },
      error: (error) => {
        console.error('Erro ao buscar candidatos.', error);

        if (error.status === 403 || error.status === 401) {
          this.errorMessage = 'Sessão expirada ou não autorizada. Faça login novamente.';
        } else {
          this.errorMessage = 'Não foi possível carregar a lista de candidatos.';
        }

        this.loading = false;
      }
    });
  }

  public updateCandidate(candidate: CandidateResponseDTO): void {
    if (candidate && candidate.id) {
      this.router.navigate(['/candidato/atualizar', candidate.id]);
    } else {
      console.error('ID do candidato não informado na resposta da API.');
    }
  }

  public deleteCandidate(idCandidate: string): void {
    if (!idCandidate) {
      console.error('ID do candidato inválido para exclusão.');
      return;
    }

    // Navega para a tela de confirmação de exclusão
    this.router.navigate(['/candidato/deletar', idCandidate]);
  }
}
