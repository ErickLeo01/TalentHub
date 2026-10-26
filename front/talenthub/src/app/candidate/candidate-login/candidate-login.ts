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

      // Descodifica a payload do token JWT (parte do meio)
      const payloadBase64 = response.token.split('.')[1];
      const decodedPayload = JSON.parse(atob(payloadBase64));

      // Extrai o ID (no Spring Security costuma vir no campo 'sub' ou 'id')
      const candidateId = decodedPayload.sub || decodedPayload.id;

      // Redireciona com o ID extraído do token
      this.router.navigate(['/candidato/atualizar', candidateId]);
    },
    error: (error) => {
      console.error('Erro ao tentar fazer login. Tente novamente.', error);
    }
  });
}
}
