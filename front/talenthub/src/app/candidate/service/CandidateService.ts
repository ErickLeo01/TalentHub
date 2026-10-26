import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

  private apiUrl = 'http://localhost:8081/candidato';

  constructor(private http: HttpClient) {}

  public createCandidate(candidate: CandidateDTO): Observable<CandidateResponseDTO> {
    return this.http.post<CandidateResponseDTO>(`${this.apiUrl}/criar`, candidate);
  }

  public updateCandidate(candidateId: string, candidate: CandidateUpdateDTO): Observable<CandidateResponseDTO> {
    return this.http.put<CandidateResponseDTO>(`${this.apiUrl}/atualizar/${candidateId}`, candidate);
  }

  public deleteCandidate(id: string): Observable<string> {
    return this.http.delete(`${this.apiUrl}/deletar/${id}`, { responseType: 'text' });
  }

  public findCandidateByName(name: string): Observable<CandidateResponseDTO[]> {
    let params = new HttpParams();

    if (name && name.trim() !== '') {
      params = params.set('name', name.trim());
    }

    return this.http.get<CandidateResponseDTO[]>(`${this.apiUrl}/buscar`, { params });
  }

  public findAllCandidates(): Observable<CandidateResponseDTO[]> {
    return this.http.get<CandidateResponseDTO[]>(`${this.apiUrl}/buscar`);
  }

  public findCandidateById(candidateId: string): Observable<CandidateResponseDTO> {
    return this.http.get<CandidateResponseDTO>(`${this.apiUrl}/${candidateId}`);
  }

  public findMyProfile(): Observable<CandidateResponseDTO> {
    return this.http.get<CandidateResponseDTO>(`${this.apiUrl}/me`);
  }

  public loginCandidate(candidateLogin: CandidateLoginDTO): Observable<CandidateLoginResponseDTO> {
    return this.http.post<CandidateLoginResponseDTO>(`${this.apiUrl}/login`, candidateLogin);
  }
}
