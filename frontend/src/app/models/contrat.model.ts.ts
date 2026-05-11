export type StatusContrat = 'ENCOURS' | 'VALIDE' | 'RESILIE';
export type TypeLogement = 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
export type NiveauCouverture = 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';

export interface ContratAssurance {
  id?: number;
  type?: string;
  dateSouscription?: string;
  status: StatusContrat;
  dateValidation?: string;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
  clientId: number;
}

export interface ContratAssuranceAutomobile extends ContratAssurance {
  immatriculationVehicule: string;
  marqueVehicule: string;
  modeleVehicule: string;
}

export interface ContratAssuranceHabitation extends ContratAssurance {
  typeLogement: TypeLogement;
  adresseLogement: string;
  superficie: number;
}

export interface ContratAssuranceSante extends ContratAssurance {
  niveauCouverture: NiveauCouverture;
  nombrePersonnesCouvertes: number;
}