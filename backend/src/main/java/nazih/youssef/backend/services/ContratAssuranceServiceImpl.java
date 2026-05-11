package nazih.youssef.backend.services;

import lombok.AllArgsConstructor;
import nazih.youssef.backend.dtos.*;
import nazih.youssef.backend.entities.*;
import nazih.youssef.backend.enums.StatusContrat;
import nazih.youssef.backend.exceptions.ClientNotFoundException;
import nazih.youssef.backend.exceptions.ContratNotFoundException;
import nazih.youssef.backend.mappers.AssuranceMapperImpl;
import nazih.youssef.backend.repositories.ClientRepository;
import nazih.youssef.backend.repositories.ContratAssuranceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ContratAssuranceServiceImpl implements ContratAssuranceService {

    private ContratAssuranceRepository contratRepository;
    private ClientRepository clientRepository;
    private AssuranceMapperImpl dtoMapper;

    @Override
    public ContratAssuranceAutomobileDTO saveContratAuto(ContratAssuranceAutomobileDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        ContratAssuranceAutomobile contrat = dtoMapper.fromContratAutoDTO(dto);
        contrat.setClient(client);
        if (contrat.getStatus() == null) contrat.setStatus(StatusContrat.ENCOURS);
        if (contrat.getDateSouscription() == null) contrat.setDateSouscription(new Date());
        ContratAssuranceAutomobile saved = contratRepository.save(contrat);
        return dtoMapper.fromContratAuto(saved);
    }

    @Override
    public ContratAssuranceHabitationDTO saveContratHabitation(ContratAssuranceHabitationDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        ContratAssuranceHabitation contrat = dtoMapper.fromContratHabitationDTO(dto);
        contrat.setClient(client);
        if (contrat.getStatus() == null) contrat.setStatus(StatusContrat.ENCOURS);
        if (contrat.getDateSouscription() == null) contrat.setDateSouscription(new Date());
        ContratAssuranceHabitation saved = contratRepository.save(contrat);
        return dtoMapper.fromContratHabitation(saved);
    }

    @Override
    public ContratAssuranceSanteDTO saveContratSante(ContratAssuranceSanteDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        ContratAssuranceSante contrat = dtoMapper.fromContratSanteDTO(dto);
        contrat.setClient(client);
        if (contrat.getStatus() == null) contrat.setStatus(StatusContrat.ENCOURS);
        if (contrat.getDateSouscription() == null) contrat.setDateSouscription(new Date());
        ContratAssuranceSante saved = contratRepository.save(contrat);
        return dtoMapper.fromContratSante(saved);
    }

    @Override
    public ContratAssuranceDTO getContrat(Long id) throws ContratNotFoundException {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ContratNotFoundException("Contrat not found"));
        return mapContrat(contrat);
    }

    @Override
    public List<ContratAssuranceDTO> listContrats() {
        return contratRepository.findAll().stream()
                .map(this::mapContrat)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratAssuranceDTO> listContratsByClient(Long clientId) {
        return contratRepository.findByClientId(clientId).stream()
                .map(this::mapContrat)
                .collect(Collectors.toList());
    }

    @Override
    public ContratAssuranceDTO validerContrat(Long id) throws ContratNotFoundException {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ContratNotFoundException("Contrat not found"));
        contrat.setStatus(StatusContrat.VALIDE);
        contrat.setDateValidation(new Date());
        contratRepository.save(contrat);
        return mapContrat(contrat);
    }

    @Override
    public ContratAssuranceDTO resilierContrat(Long id) throws ContratNotFoundException {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ContratNotFoundException("Contrat not found"));
        contrat.setStatus(StatusContrat.RESILIE);
        contratRepository.save(contrat);
        return mapContrat(contrat);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    private ContratAssuranceDTO mapContrat(ContratAssurance contrat) {
        if (contrat instanceof ContratAssuranceAutomobile) {
            return dtoMapper.fromContratAuto((ContratAssuranceAutomobile) contrat);
        } else if (contrat instanceof ContratAssuranceHabitation) {
            return dtoMapper.fromContratHabitation((ContratAssuranceHabitation) contrat);
        } else {
            return dtoMapper.fromContratSante((ContratAssuranceSante) contrat);
        }
    }
}