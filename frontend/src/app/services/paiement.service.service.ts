import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Paiement } from '../models/paiement.model.ts';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PaiementService {
  private http = inject(HttpClient);
  private baseUrl = `${environment.backendHost}/paiements`;

  list(): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(this.baseUrl);
  }

  get(id: number): Observable<Paiement> {
    return this.http.get<Paiement>(`${this.baseUrl}/${id}`);
  }

  listByContrat(contratId: number): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(`${environment.backendHost}/contrats/${contratId}/paiements`);
  }

  save(paiement: Paiement): Observable<Paiement> {
    return this.http.post<Paiement>(this.baseUrl, paiement);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}