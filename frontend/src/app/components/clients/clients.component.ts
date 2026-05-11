import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { catchError, Observable, throwError } from 'rxjs';
import { Client } from '../../models/client.model';
import { ClientService } from '../../services/client.service.service';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {
  clientsObservable!: Observable<Client[]>;
  clientFormGroup!: FormGroup;
  errorMessage!: string;

  constructor(private fb: FormBuilder, private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientFormGroup = this.fb.group({
      name: this.fb.control(''),
      email: this.fb.control('')
    });
    this.handleGetClients();
  }

  handleGetClients() {
    this.clientsObservable = this.clientService.list().pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  handleSaveClient() {
    let client: Client = this.clientFormGroup.value;
    this.clientService.save(client).subscribe({
      next: (data) => {
        alert("Client saved successfully");
        this.clientFormGroup.reset();
        this.handleGetClients();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  handleDeleteClient(client: Client) {
    let confirmation = confirm("Are you sure?");
    if (!confirmation) return;
    this.clientService.delete(client.id!).subscribe({
      next: (data) => {
        this.handleGetClients();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}