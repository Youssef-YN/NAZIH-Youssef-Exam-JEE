export type TypePaiement = 'MENSUALITE' | 'ANNUEL' | 'EXCEPTIONNEL';

export interface Paiement {
  id?: number;
  date?: string;
  montant: number;
  type: TypePaiement;
  contratId: number;
}