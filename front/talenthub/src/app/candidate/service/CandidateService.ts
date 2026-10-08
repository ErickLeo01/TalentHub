import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CandidateDTO } from '../interface/candidate-dto.interface';
import { CandidateResponseDTO } from '../interface/candidate-resp

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
    candidate: CandidateDTO
  ): Observable<CandidateResponseDTO> {
    return this.http.put<CandidateResponseDTO>(
      `${this.apiUrl}/atualizar/${candidateId}`,
      candidate
    );
  }

  public deleteCandidate(
    candidateId: string
  ): Observable<string> {
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
}