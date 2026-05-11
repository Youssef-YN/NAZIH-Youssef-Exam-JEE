import { Routes } from '@angular/router';
import { ClientsComponent } from './components/clients/clients.component';
import { ContratsComponent } from './components/contrats/contrats.component';
import { HomeComponent } from './components/home/home.component';
import { PaiementsComponent } from './components/paiements/paiements.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'clients', component: ClientsComponent},
    {path: 'contrats', component: ContratsComponent},
    {path: 'paiements', component: PaiementsComponent}
];
