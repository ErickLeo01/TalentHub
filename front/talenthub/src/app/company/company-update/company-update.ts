import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CompanyUpdateDTO } from '../interface/company-update-dto.interface';
import { CompanyService } from '../service/CompanyService';
import { CompanyResponseDTO } from '../interface/company-response-interface';

@Component({
  imports: [FormsModule, CommonModule],
  selector: 'app-company-update',
  styleUrl: './company-update.css',
  templateUrl: './company-update.html',
})
export class CompanyUpdate implements OnInit {

  companyId: string = '';

  loading: boolean = true;
  saving: boolean = false;
  message: string = '';
  errorMessage: string = '';

  company: CompanyUpdateDTO = {
    name: '',
    email: '',
    password: '',
    cnpj: '',
    description: '',
    address: ''
  };

  constructor(
    private companyService: CompanyService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.companyId = this.route.snapshot.paramMap.get('id') ?? '';

    if (!this.companyId) {
      this.errorMessage = 'ID da empresa não informado.';
      this.loading = false;
      return;
    }

    this.findCompanyById();
  }

  public findCompanyById(): void {
    this.loading = true;
    this.errorMessage = '';

    this.companyService.findCompanyById(this.companyId).subscribe({
      next: (response: CompanyResponseDTO) => {
        this.company.name = response.name;
        this.company.email = response.email;
        this.company.cnpj = response.cnpj;
        this.company.description = response.description;
        this.company.address = response.address;

        this.loading = false;
      },
      error: (error) => {
        console.error('Erro ao buscar empresa:', error);
        this.errorMessage = 'Erro ao buscar empresa. Tente novamente.';
        this.loading = false;
      }
    });
  }

  public updateCompany(): void {
    if (this.saving || this.loading || !this.companyId) {
      return;
    }

    this.message = '';
    this.errorMessage = '';

    const companyUpdate: CompanyUpdateDTO = {};

    if (this.company.name?.trim()) {
      companyUpdate.name = this.company.name.trim();
    }

    if (this.company.email?.trim()) {
      companyUpdate.email = this.company.email.trim();
    }

    if (this.company.password?.trim()) {
      companyUpdate.password = this.company.password;
    }

    if (this.company.cnpj?.trim()) {
      companyUpdate.cnpj = this.company.cnpj.trim();
    }

    if (this.company.address?.trim()) {
      companyUpdate.address = this.company.address.trim();
    }

    if (this.company.description !== undefined &&
        this.company.description !== null) {
      companyUpdate.description = this.company.description;
    }

    this.saving = true;

    this.companyService.updateCompany(
      this.companyId,
      companyUpdate
    ).subscribe({
      next: (response: CompanyResponseDTO) => {
        this.saving = false;
        this.message = 'Cadastro atualizado com sucesso!';

        console.log('Empresa atualizada:', response);
        this.router.navigate(['/empresa/perfil']);
      },
      error: (error) => {
        this.saving = false;
        console.error('Erro ao atualizar empresa:', error);

        this.errorMessage =
          error.error?.message ??
          'Não foi possível atualizar o cadastro. Verifique os dados e tente novamente.';
      }
    });
  }
}
