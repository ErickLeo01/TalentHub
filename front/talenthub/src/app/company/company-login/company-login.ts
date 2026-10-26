import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CompanyService } from '../service/CompanyService';
import { CompanyLoginDTO } from '../interface/company-login-interface';
import { CompanyLoginResponseDTO } from '../interface/company-login-interface-response';

@Component({
  imports: [FormsModule, RouterLink],
  selector: 'app-company-login',
  styleUrl: './company-login.css',
  templateUrl: './company-login.html',
})
export class CompanyLogin {

  companyLogin: CompanyLoginDTO = {
    email: '',
    password: ''
  };

  constructor(
    private companyService: CompanyService,
    private router: Router
  ) {}

  public loginCompany(): void {
    this.companyService.loginCompany(this.companyLogin).subscribe({
      next: (response: CompanyLoginResponseDTO) => {
        // Armazena o token de autenticação
        localStorage.setItem('token', response.token);

        console.log('Login feito com sucesso!');

        // Redireciona para a rota que exibe os candidatos
        this.router.navigate(['/candidatos']);
      },
      error: (error) => {
        console.error('Erro ao tentar fazer login. Tente novamente.', error);
      }
    });
  }
}

