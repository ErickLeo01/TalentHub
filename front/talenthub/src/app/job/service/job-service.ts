import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobDTO } from '../interface/job-dto-interface';
import { JobResponseDTO } from '../interface/job-response.interface';

@Injectable({
  providedIn: 'root'
})

export class JobService {

  private apiUrl = 'http://localhost:8080/vagas';

  constructor(private http: HttpClient) {}

  public createJob(job: JobDTO): Observable<JobResponseDTO> {
    return this.http.post<JobResponseDTO>(
      `${this.apiUrl}/criar`,
      job
    );
  }

  public updateJob(
    jobId: string,
    job: JobDTO
  ): Observable<JobResponseDTO> {
    return this.http.put<JobResponseDTO>(
      `${this.apiUrl}/atualizar/${jobId}`,
      job
    );
  }

  public deleteJob(jobId: string): Observable<string> {
    return this.http.delete(
      `${this.apiUrl}/deletar/${jobId}`,
      { responseType: 'text' }
    );
  }

  public findJobByName(
    name: string
  ): Observable<JobResponseDTO[]> {
    return this.http.get<JobResponseDTO[]>(
      `${this.apiUrl}/buscar?name=${encodeURIComponent(name)}`
    );
  }
}
