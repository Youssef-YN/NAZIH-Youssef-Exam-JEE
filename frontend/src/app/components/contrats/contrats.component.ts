import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { catchError, Observable, throwError } from 'rxjs';
import { ContratAssurance, ContratAssuranceAutomobile, ContratAssuranceHabitation, ContratAssuranceSante } from '../../models/contrat.model.ts';
import { ContratService } from '../../services/contrat.service.service.js';

@Component({
  selector: 'app-contrats',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './contrats.component.html'
})
export class ContratsComponent implements OnInit {
  contratsObservable!: Observable<ContratAssurance[]>;
  contratFormGroup!: FormGroup;
  errorMessage!: string;

  constructor(private fb: FormBuilder, private contratService: ContratService) {}

  ngOnInit(): void {
    this.contratFormGroup = this.fb.group({
      type: this.fb.control('AUTO'),
      clientId: this.fb.control(null),
      montantCotisation: this.fb.control(0),
      dureeContrat: this.fb.control(12),
      tauxCouverture: this.fb.control(0),
      
      immatriculationVehicule: this.fb.control(null),
      marqueVehicule: this.fb.control(null),
      modeleVehicule: this.fb.control(null),

      typeLogement: this.fb.control('APPARTEMENT'),
      adresseLogement: this.fb.control(null),
      superficie: this.fb.control(0),

      niveauCouverture: this.fb.control('BASIQUE'),
      nombrePersonnesCouvertes: this.fb.control(1)
    });
    this.handleGetContrats();
  }

  handleGetContrats() {
    this.contratsObservable = this.contratService.list().pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  handleSaveContrat() {
    let type = this.contratFormGroup.value.type;
    if (type == 'AUTO') {
      let contrat: ContratAssuranceAutomobile = this.contratFormGroup.value;
      this.contratService.saveAuto(contrat).subscribe({
        next: (data) => {
          alert("Contrat Auto saved");
          this.contratFormGroup.reset({ type: 'AUTO' });
          this.handleGetContrats();
        },
        error: (err) => { console.log(err); }
      });
    } else if (type == 'HABITATION') {
      let contrat: ContratAssuranceHabitation = this.contratFormGroup.value;
      this.contratService.saveHabitation(contrat).subscribe({
        next: (data) => {
          alert("Contrat Habitation saved");
          this.contratFormGroup.reset({ type: 'HABITATION' });
          this.handleGetContrats();
        },
        error: (err) => { console.log(err); }
      });
    } else if (type == 'SANTE') {
      let contrat: ContratAssuranceSante = this.contratFormGroup.value;
      this.contratService.saveSante(contrat).subscribe({
        next: (data) => {
          alert("Contrat Sante saved");
          this.contratFormGroup.reset({ type: 'SANTE' });
          this.handleGetContrats();
        },
        error: (err) => { console.log(err); }
      });
    }
  }

  handleValiderContrat(contrat: ContratAssurance) {
    this.contratService.valider(contrat.id!).subscribe({
      next: (data) => {
        alert("Contrat validé");
        this.handleGetContrats();
      },
      error: (err) => { console.log(err); }
    });
  }

  handleResilierContrat(contrat: ContratAssurance) {
    this.contratService.resilier(contrat.id!).subscribe({
      next: (data) => {
        alert("Contrat résilié");
        this.handleGetContrats();
      },
      error: (err) => { console.log(err); }
    });
  }

  handleDeleteContrat(contrat: ContratAssurance) {
    let confirmation = confirm("Are you sure?");
    if (!confirmation) return;
    this.contratService.delete(contrat.id!).subscribe({
      next: (data) => { this.handleGetContrats(); },
      error: (err) => { console.log(err); }
    });
  }
}