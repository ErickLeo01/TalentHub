
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyService } from '../service/CompanyService';
import { CompanyResponseDTO } from '../interface/company-response-interface';

@Component({
  selector: 'app-company-profile',
  imports: [CommonModule],
  templateUrl: './company-profile.html',
  styleUrl: './company-profile.css',
})
export class CompanyProfile implements OnInit {

  // Guarda os dados da empresa.
  company: CompanyResponseDTO | null = null;

  // Controla o carregamento e as mensagens de erro.
  loading: boolean = false;
  errorMessage: string = '';

  constructor(
    private companyService: CompanyService,
    private router: Router
  ) {}

  // Executa quando a página é aberta.
  ngOnInit(): void {
    this.findCompanyProfile();
  }

  // Busca o perfil da empresa autenticada no backend.
  public findCompanyProfile(): void {
    this.loading = true;
    this.errorMessage = '';

    this.companyService.findMyProfile().subscribe({
      next: (response: CompanyResponseDTO) => {
        this.company = response;
        this.loading = false;
        console.log('Perfil da empresa carregado com sucesso.');
      },

      error: (error: any) => {
        console.error('Erro ao buscar perfil da empresa:', error);

        this.errorMessage =
          'Não foi possível buscar o perfil da empresa.';

        this.loading = false;
      }
    });
  }

  // Abre a página de atualização do cadastro da empresa.
  public updateCompany(): void {
    if (!this.company || this.loading) {
      return;
    }

    this.router.navigate([
      '/empresa/update',
      this.company.id
    ]);
  }
}
