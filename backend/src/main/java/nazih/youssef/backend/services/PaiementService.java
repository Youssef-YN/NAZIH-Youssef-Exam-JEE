package nazih.youssef.backend.services;

import nazih.youssef.backend.dtos.PaiementDTO;
import nazih.youssef.backend.exceptions.ContratNotFoundException;
import nazih.youssef.backend.exceptions.PaiementNotFoundException;

import java.util.List;

public interface PaiementService {
    PaiementDTO savePaiement(PaiementDTO dto) throws ContratNotFoundException;
    PaiementDTO getPaiement(Long id) throws PaiementNotFoundException;
    List<PaiementDTO> listPaiements();
    List<PaiementDTO> listPaiementsByContrat(Long contratId);
    void deletePaiement(Long id);
}