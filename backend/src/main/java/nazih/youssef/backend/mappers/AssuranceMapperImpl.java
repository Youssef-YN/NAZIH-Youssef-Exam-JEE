package nazih.youssef.backend.mappers;

import nazih.youssef.backend.dtos.*;
import nazih.youssef.backend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AssuranceMapperImpl {

    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    public ContratAssuranceAutomobileDTO fromContratAuto(ContratAssuranceAutomobile contrat) {
        ContratAssuranceAutomobileDTO dto = new ContratAssuranceAutomobileDTO();
        BeanUtils.copyProperties(contrat, dto);
        if (contrat.getClient() != null) dto.setClientId(contrat.getClient().getId());
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratAssuranceAutomobile fromContratAutoDTO(ContratAssuranceAutomobileDTO dto) {
        ContratAssuranceAutomobile contrat = new ContratAssuranceAutomobile();
        BeanUtils.copyProperties(dto, contrat);
        return contrat;
    }

    public ContratAssuranceHabitationDTO fromContratHabitation(ContratAssuranceHabitation contrat) {
        ContratAssuranceHabitationDTO dto = new ContratAssuranceHabitationDTO();
        BeanUtils.copyProperties(contrat, dto);
        if (contrat.getClient() != null) dto.setClientId(contrat.getClient().getId());
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratAssuranceHabitation fromContratHabitationDTO(ContratAssuranceHabitationDTO dto) {
        ContratAssuranceHabitation contrat = new ContratAssuranceHabitation();
        BeanUtils.copyProperties(dto, contrat);
        return contrat;
    }

    public ContratAssuranceSanteDTO fromContratSante(ContratAssuranceSante contrat) {
        ContratAssuranceSanteDTO dto = new ContratAssuranceSanteDTO();
        BeanUtils.copyProperties(contrat, dto);
        if (contrat.getClient() != null) dto.setClientId(contrat.getClient().getId());
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratAssuranceSante fromContratSanteDTO(ContratAssuranceSanteDTO dto) {
        ContratAssuranceSante contrat = new ContratAssuranceSante();
        BeanUtils.copyProperties(dto, contrat);
        return contrat;
    }

    public PaiementDTO fromPaiement(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        BeanUtils.copyProperties(paiement, dto);
        if (paiement.getContratAssurance() != null) dto.setContratId(paiement.getContratAssurance().getId());
        return dto;
    }

    public Paiement fromPaiementDTO(PaiementDTO dto) {
        Paiement paiement = new Paiement();
        BeanUtils.copyProperties(dto, paiement);
        return paiement;
    }
}