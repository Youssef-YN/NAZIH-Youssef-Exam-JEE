package nazih.youssef.backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nazih.youssef.backend.dtos.PaiementDTO;
import nazih.youssef.backend.exceptions.ContratNotFoundException;
import nazih.youssef.backend.exceptions.PaiementNotFoundException;
import nazih.youssef.backend.services.PaiementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PaiementRestController {
    private PaiementService paiementService;

    @GetMapping("/paiements")
    public List<PaiementDTO> paiements() {
        return paiementService.listPaiements();
    }

    @GetMapping("/paiements/{id}")
    public PaiementDTO getPaiement(@PathVariable Long id) throws PaiementNotFoundException {
        return paiementService.getPaiement(id);
    }

    @GetMapping("/contrats/{contratId}/paiements")
    public List<PaiementDTO> getPaiementsByContrat(@PathVariable Long contratId) {
        return paiementService.listPaiementsByContrat(contratId);
    }

    @PostMapping("/paiements")
    public PaiementDTO savePaiement(@RequestBody PaiementDTO dto) throws ContratNotFoundException {
        return paiementService.savePaiement(dto);
    }

    @DeleteMapping("/paiements/{id}")
    public void deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
    }
}