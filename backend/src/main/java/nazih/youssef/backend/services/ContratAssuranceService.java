package nazih.youssef.backend.services;

import nazih.youssef.backend.dtos.ContratAssuranceAutomobileDTO;
import nazih.youssef.backend.dtos.ContratAssuranceDTO;
import nazih.youssef.backend.dtos.ContratAssuranceHabitationDTO;
import nazih.youssef.backend.dtos.ContratAssuranceSanteDTO;
import nazih.youssef.backend.exceptions.ClientNotFoundException;
import nazih.youssef.backend.exceptions.ContratNotFoundException;

import java.util.List;

public interface ContratAssuranceService {
    ContratAssuranceAutomobileDTO saveContratAuto(ContratAssuranceAutomobileDTO dto) throws ClientNotFoundException;
    ContratAssuranceHabitationDTO saveContratHabitation(ContratAssuranceHabitationDTO dto) throws ClientNotFoundException;
    ContratAssuranceSanteDTO saveContratSante(ContratAssuranceSanteDTO dto) throws ClientNotFoundException;

    ContratAssuranceDTO getContrat(Long id) throws ContratNotFoundException;
    List<ContratAssuranceDTO> listContrats();
    List<ContratAssuranceDTO> listContratsByClient(Long clientId);

    ContratAssuranceDTO validerContrat(Long id) throws ContratNotFoundException;
    ContratAssuranceDTO resilierContrat(Long id) throws ContratNotFoundException;
    void deleteContrat(Long id);
}