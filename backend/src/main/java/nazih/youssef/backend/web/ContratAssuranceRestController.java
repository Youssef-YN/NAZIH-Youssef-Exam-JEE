package nazih.youssef.backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nazih.youssef.backend.dtos.ContratAssuranceAutomobileDTO;
import nazih.youssef.backend.dtos.ContratAssuranceDTO;
import nazih.youssef.backend.dtos.ContratAssuranceHabitationDTO;
import nazih.youssef.backend.dtos.ContratAssuranceSanteDTO;
import nazih.youssef.backend.exceptions.ClientNotFoundException;
import nazih.youssef.backend.exceptions.ContratNotFoundException;
import nazih.youssef.backend.services.ContratAssuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ContratAssuranceRestController {
    private ContratAssuranceService contratService;

    @GetMapping("/contrats")
    public List<ContratAssuranceDTO> contrats() {
        return contratService.listContrats();
    }

    @GetMapping("/contrats/{id}")
    public ContratAssuranceDTO getContrat(@PathVariable(name = "id") Long id) throws ContratNotFoundException {
        return contratService.getContrat(id);
    }

    @GetMapping("/clients/{clientId}/contrats")
    public List<ContratAssuranceDTO> getContratsByClient(@PathVariable Long clientId) {
        return contratService.listContratsByClient(clientId);
    }

    @PostMapping("/contrats/auto")
    public ContratAssuranceAutomobileDTO saveContratAuto(@RequestBody ContratAssuranceAutomobileDTO dto) throws ClientNotFoundException {
        return contratService.saveContratAuto(dto);
    }

    @PostMapping("/contrats/habitation")
    public ContratAssuranceHabitationDTO saveContratHabitation(@RequestBody ContratAssuranceHabitationDTO dto) throws ClientNotFoundException {
        return contratService.saveContratHabitation(dto);
    }

    @PostMapping("/contrats/sante")
    public ContratAssuranceSanteDTO saveContratSante(@RequestBody ContratAssuranceSanteDTO dto) throws ClientNotFoundException {
        return contratService.saveContratSante(dto);
    }

    @PutMapping("/contrats/{id}/valider")
    public ContratAssuranceDTO validerContrat(@PathVariable Long id) throws ContratNotFoundException {
        return contratService.validerContrat(id);
    }

    @PutMapping("/contrats/{id}/resilier")
    public ContratAssuranceDTO resilierContrat(@PathVariable Long id) throws ContratNotFoundException {
        return contratService.resilierContrat(id);
    }

    @DeleteMapping("/contrats/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
    }
}