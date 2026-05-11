package nazih.youssef.backend.services;

import lombok.AllArgsConstructor;
import nazih.youssef.backend.dtos.PaiementDTO;
import nazih.youssef.backend.entities.ContratAssurance;
import nazih.youssef.backend.entities.Paiement;
import nazih.youssef.backend.exceptions.ContratNotFoundException;
import nazih.youssef.backend.exceptions.PaiementNotFoundException;
import nazih.youssef.backend.mappers.AssuranceMapperImpl;
import nazih.youssef.backend.repositories.ContratAssuranceRepository;
import nazih.youssef.backend.repositories.PaiementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private PaiementRepository paiementRepository;
    private ContratAssuranceRepository contratRepository;
    private AssuranceMapperImpl dtoMapper;

    @Override
    public PaiementDTO savePaiement(PaiementDTO dto) throws ContratNotFoundException {
        ContratAssurance contrat = contratRepository.findById(dto.getContratId())
                .orElseThrow(() -> new ContratNotFoundException("Contrat not found"));
        Paiement paiement = dtoMapper.fromPaiementDTO(dto);
        paiement.setContratAssurance(contrat);
        if (paiement.getDate() == null) paiement.setDate(new Date());
        Paiement saved = paiementRepository.save(paiement);
        return dtoMapper.fromPaiement(saved);
    }

    @Override
    public PaiementDTO getPaiement(Long id) throws PaiementNotFoundException {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new PaiementNotFoundException("Paiement not found"));
        return dtoMapper.fromPaiement(paiement);
    }

    @Override
    public List<PaiementDTO> listPaiements() {
        return paiementRepository.findAll().stream()
                .map(p -> dtoMapper.fromPaiement(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaiementDTO> listPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratAssuranceId(contratId).stream()
                .map(p -> dtoMapper.fromPaiement(p))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}