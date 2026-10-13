
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyService } from '../service/CompanyService';
import { CompanyResponseDTO } from '../interface/company-response-interface';

@Component({
  selector: 'app-company-list',
  imports: [CommonModule],
  templateUrl: './company-list.html',
  styleUrl: './company-list.css',
})
export class CompanyList implements OnInit {

  companies: CompanyResponseDTO[] = [];

  loading: boolean = false;
  errorMessage: string = '';

  constructor(
    private companyService: CompanyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.findCompanies();
  }

  // Busca as empresas no backend.
  public findCompanies(): void {
    this.loading = true;
    this.errorMessage = '';

    this.companyService.findCompanyByName('').subscribe({
      next: (response: CompanyResponseDTO[]) => {
        this.companies = response;
        this.loading = false;
      },
      error: (error: any) => {
        console.error('Erro ao buscar empresas:', error);
        this.errorMessage = 'Não foi possível buscar as empresas.';
        this.loading = false;
      }
    });
  }

  // Abre a página de atualização da empresa selecionada.
  public updateCompany(company: CompanyResponseDTO): void {
    this.router.navigate(['/empresas/update', company.id]);
  }
}
