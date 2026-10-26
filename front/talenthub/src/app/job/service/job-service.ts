import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobDTO } from '../interface/job-dto-interface';
import { JobResponseDTO } from '../interface/job-response.interface';
import { JobUpdateDTO } from '../interface/job-update-dto.interface';

@Injectable({
  providedIn: 'root'
})

export class JobService {

  private apiUrl = 'http://localhost:8081/vagas';

  constructor(private http: HttpClient) {}

  public createJob(job: JobDTO): Observable<JobResponseDTO> {
    return this.http.post<JobResponseDTO>(
      `${this.apiUrl}/criar`,
      job
    );
  }

  public updateJob(
  jobId: string,
  job: JobUpdateDTO
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

public findJobById(jobId: string): Observable<JobResponseDTO> {
  return this.http.get<JobResponseDTO>(
    `${this.apiUrl}/${jobId}`
  );
}


}
