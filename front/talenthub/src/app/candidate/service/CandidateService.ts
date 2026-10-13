
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CandidateDTO } from '../interface/candidate-dto.interface';
import { CandidateUpdateDTO } from '../interface/candidate-update-dto.interface';
import { CandidateResponseDTO } from '../interface/candidate-response.interface';
import { CandidateLoginDTO } from '../interface/candidate-login.interface';
import { CandidateLoginResponseDTO } from '../interface/candidate-login-interface-response';

@Injectable({
  providedIn: 'root'
})
export class CandidateService {

  private apiUrl = 'http://localhost:8080/candidato';

  constructor(private http: HttpClient) {}

  public createCandidate(
    candidate: CandidateDTO
  ): Observable<CandidateResponseDTO> {
    return this.http.post<CandidateResponseDTO>(
      `${this.apiUrl}/criar`,
      candidate
    );
  }

  public updateCandidate(
    candidateId: string,
    candidate: CandidateUpdateDTO
  ): Observable<CandidateResponseDTO> {
    return this.http.put<CandidateResponseDTO>(
      `${this.apiUrl}/atualizar/${candidateId}`,
      candidate
    );
  }

  public deleteCandidate(candidateId: string): Observable<string> {
    return this.http.delete<string>(
      `${this.apiUrl}/deletar/${candidateId}`
    );
  }

  public findCandidateByName(
    name: string
  ): Observable<CandidateResponseDTO[]> {
    return this.http.get<CandidateResponseDTO[]>(
      `${this.apiUrl}/buscar?name=${encodeURIComponent(name)}`
    );
  }

  public findCandidateById(
    candidateId: string
  ): Observable<CandidateResponseDTO> {
    return this.http.get<CandidateResponseDTO>(
      `${this.apiUrl}/${candidateId}`
    );
  }

  // Busca o perfil do candidato autenticado.
  public findMyProfile(): Observable<CandidateResponseDTO> {
    return this.http.get<CandidateResponseDTO>(
      `${this.apiUrl}/me`
    );
  }

  public loginCandidate(
    candidateLogin: CandidateLoginDTO
  ): Observable<CandidateLoginResponseDTO> {
    return this.http.post<CandidateLoginResponseDTO>(
      `${this.apiUrl}/login`,
      candidateLogin
    );
  }
}
