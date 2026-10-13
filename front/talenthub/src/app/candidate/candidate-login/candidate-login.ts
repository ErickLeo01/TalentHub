import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CandidateService } from '../service/CandidateService';
import { CandidateLoginDTO } from '../interface/candidate-login.interface';
import { CandidateLoginResponseDTO } from '../interface/candidate-login-interface-response';

@Component({
  imports: [FormsModule],
  selector: 'app-candidate-login',
  styleUrl: './candidate-login.css',
  templateUrl: './candidate-login.html',
})

export class CandidateLogin {

  candidateLogin: CandidateLoginDTO = {
    username: '',
    password: ''
  }

  constructor(private candidateService: CandidateService, private router: Router) {}

  public loginCandidate(): void {
    this.candidateService.loginCandidate(this.candidateLogin).subscribe({
      next: (response: CandidateLoginResponseDTO) => {
        localStorage.setItem('token', response.token);
        console.log('Login feito com sucesso!');
        this.router.navigate(['/candidatos']);
      },
      error: (error) => {
        console.error('Erro ao tentar fazer login. Tente novamente.', error);
      }
    })
  }
}
