import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CompanyDTO } from '../interface/company-dto-interface';
import { CompanyService } from '../service/CompanyService';
import { CompanyResponseDTO } from '../interface/company-response-interface';

@Component({
  imports: [FormsModule],
  selector: 'app-company-create',
  styleUrl: './company-create.css',
  templateUrl: './company-create.html',
})
export class CompanyCreate {

  constructor(private companyService: CompanyService) {}

  company: CompanyDTO = {
    name: '',
    email: '',
    password: '',
    cnpj: '',
    description: '',
    address: ''
  }

  public createCompany(): void {
    this.companyService.createCompany(this.company).subscribe({
      next: (response: CompanyResponseDTO) => {
        console.log('Empresa criada com sucesso!', response);

        this.company = {
          name: '',
          email: '',
          password: '',
          cnpj: '',
          description: '',
          address: ''
        };
      },
      error:(error) => {
        console.error('Erro ao criar empresa:', error);
      }
    })

  }
}
