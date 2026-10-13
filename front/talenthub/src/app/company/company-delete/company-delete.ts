import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CompanyService } from '../service/CompanyService';

@Component({
  selector: 'app-company-delete',
  imports: [CommonModule, RouterLink],
  templateUrl: './company-delete.html',
  styleUrl: './company-delete.css',
})
export class CompanyDelete implements OnInit {

  companyId: string = '';
  loading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private companyService: CompanyService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  // Obtém o ID da empresa informado na rota.
  ngOnInit(): void {
    this.companyId = this.route.snapshot.paramMap.get('id') ?? '';

    if (!this.companyId) {
      this.errorMessage = 'ID da empresa não informado.';
    }
  }

  // Solicita a exclusão da empresa.
  public deleteCompany(): void {
    if (!this.companyId || this.loading) {
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.companyService.deleteCompany(this.companyId).subscribe({
      next: () => {
       this.loading = false;
       this.router.navigate(['/empresas'], {
      queryParams: { mensagem: 'Empresa excluída com sucesso!' }
      });
      },

      error: (error) => {
        this.loading = false;

        console.error('Erro ao excluir empresa:', error);

        this.errorMessage =
          error.error?.message ??
          'Não foi possível excluir a empresa. Tente novamente.';
      }
    });
  }

  // Solicita confirmação antes de excluir.
  public confirmDelete(): void {
    if (!this.companyId || this.loading) {
      return;
    }

    const confirmed = confirm(
      'Tem certeza de que deseja excluir esta empresa? Esta ação não poderá ser desfeita.'
    );

    if (confirmed) {
      this.deleteCompany();
    }
  }
}
