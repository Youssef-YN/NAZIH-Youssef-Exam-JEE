import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import {ContratAssurance, ContratAssuranceAutomobile, ContratAssuranceHabitation, ContratAssuranceSante} from '../models/contrat.model.ts';
import { environment } from '../../environments/environment.js';

@Injectable({ providedIn: 'root' })
export class ContratService {
  private http = inject(HttpClient);
  private baseUrl = `${environment.backendHost}/contrats`;

  list(): Observable<ContratAssurance[]> {
    return this.http.get<ContratAssurance[]>(this.baseUrl);
  }

  get(id: number): Observable<ContratAssurance> {
    return this.http.get<ContratAssurance>(`${this.baseUrl}/${id}`);
  }

  listByClient(clientId: number): Observable<ContratAssurance[]> {
    return this.http.get<ContratAssurance[]>(`${environment.backendHost}/clients/${clientId}/contrats`);
  }

  saveAuto(dto: ContratAssuranceAutomobile): Observable<ContratAssuranceAutomobile> {
    return this.http.post<ContratAssuranceAutomobile>(`${this.baseUrl}/auto`, dto);
  }

  saveHabitation(dto: ContratAssuranceHabitation): Observable<ContratAssuranceHabitation> {
    return this.http.post<ContratAssuranceHabitation>(`${this.baseUrl}/habitation`, dto);
  }

  saveSante(dto: ContratAssuranceSante): Observable<ContratAssuranceSante> {
    return this.http.post<ContratAssuranceSante>(`${this.baseUrl}/sante`, dto);
  }

  valider(id: number): Observable<ContratAssurance> {
    return this.http.put<ContratAssurance>(`${this.baseUrl}/${id}/valider`, {});
  }

  resilier(id: number): Observable<ContratAssurance> {
    return this.http.put<ContratAssurance>(`${this.baseUrl}/${id}/resilier`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}