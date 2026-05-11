import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { catchError, Observable, throwError, BehaviorSubject, map, combineLatest } from 'rxjs';
import { Paiement } from '../../models/paiement.model.ts';
import { PaiementService } from '../../services/paiement.service.service.js';

@Component({
  selector: 'app-paiements',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './paiements.component.html'
})
export class PaiementsComponent implements OnInit {
  paiementsObservable!: Observable<Paiement[]>;
  pagedPaiementsObservable!: Observable<Paiement[]>;
  paiementFormGroup!: FormGroup;
  errorMessage!: string;

  currentPage = 1;
  pageSize = 5;
  totalItems = 0;
  totalPages = 0;
  private pageSubject = new BehaviorSubject<number>(1);

  constructor(private fb: FormBuilder, private paiementService: PaiementService) {}

  ngOnInit(): void {
    this.paiementFormGroup = this.fb.group({
      contratId: this.fb.control(null),
      montant: this.fb.control(0),
      type: this.fb.control('MENSUALITE')
    });
    this.handleGetPaiements();
  }

  handleGetPaiements() {
    this.paiementsObservable = this.paiementService.list().pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );

    this.pagedPaiementsObservable = combineLatest([
      this.paiementsObservable,
      this.pageSubject
    ]).pipe(
      map(([paiements, page]) => {
        this.totalItems = paiements.length;
        this.totalPages = Math.ceil(this.totalItems / this.pageSize);

        
        if (page > this.totalPages && this.totalPages > 0) {
          this.currentPage = this.totalPages;
          this.pageSubject.next(this.totalPages);
          page = this.totalPages;
        }

        const start = (page - 1) * this.pageSize;
        return paiements.slice(start, start + this.pageSize);
      })
    );
  }

  goToPage(page: number) {
    if (page < 1 || page > this.totalPages) return;
    this.currentPage = page;
    this.pageSubject.next(page);
  }

  onPageSizeChange(size: number) {
    this.pageSize = +size;
    this.currentPage = 1;
    this.pageSubject.next(1);
  }

  get pages(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }

  handleSavePaiement() {
    let paiement: Paiement = this.paiementFormGroup.value;
    this.paiementService.save(paiement).subscribe({
      next: (data) => {
        alert("Paiement saved");
        this.paiementFormGroup.reset({ type: 'MENSUALITE' });
        this.handleGetPaiements();
      },
      error: (err) => { console.log(err); }
    });
  }

  handleDeletePaiement(paiement: Paiement) {
    let confirmation = confirm("Are you sure?");
    if (!confirmation) return;
    this.paiementService.delete(paiement.id!).subscribe({
      next: (data) => { this.handleGetPaiements(); },
      error: (err) => { console.log(err); }
    });
  }
}