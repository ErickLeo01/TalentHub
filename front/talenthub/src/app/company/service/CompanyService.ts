import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompanyDTO } from '../interface/company-dto-interface';
import { CompanyUpdateDTO } from '../interface/company-update-dto.interface';
import { CompanyResponseDTO } from '../interface/company-response-interface';
import { CompanyLoginDTO } from '../interface/company-login-interface';
import { CompanyLoginResponseDTO } from '../interface/company-login-interface-response';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private apiUrl = 'http://localhost:8081/empresa';

  constructor(private http: HttpClient) {}

  public createCompany(company: CompanyDTO): Observable<CompanyResponseDTO> {
    return this.http.post<CompanyResponseDTO>(`${this.apiUrl}/criar`, company);
  }

  public updateCompany(companyId: string, company: CompanyUpdateDTO): Observable<CompanyResponseDTO> {
    return this.http.put<CompanyResponseDTO>(`${this.apiUrl}/atualizar/${companyId}`, company);
  }

  public deleteCompany(companyId: string): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/deletar/${companyId}`);
  }

  public findCompanyByName(name: string): Observable<CompanyResponseDTO[]> {
    return this.http.get<CompanyResponseDTO[]>(`${this.apiUrl}/buscar?name=${encodeURIComponent(name)}`);
  }

  public findCompanyById(companyId: string): Observable<CompanyResponseDTO> {
    return this.http.get<CompanyResponseDTO>(`${this.apiUrl}/${companyId}`);
  }

  public loginCompany(companyLogin: CompanyLoginDTO): Observable<CompanyLoginResponseDTO> {
    return this.http.post<CompanyLoginResponseDTO>(`${this.apiUrl}/login`, companyLogin);
  }


public findMyProfile(): Observable<CompanyResponseDTO> {
  return this.http.get<CompanyResponseDTO>(`${this.apiUrl}/me`);
}
}
