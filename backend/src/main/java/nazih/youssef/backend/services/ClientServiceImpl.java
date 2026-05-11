package nazih.youssef.backend.services;

import lombok.AllArgsConstructor;
import nazih.youssef.backend.dtos.ClientDTO;
import nazih.youssef.backend.entities.Client;
import nazih.youssef.backend.exceptions.ClientNotFoundException;
import nazih.youssef.backend.mappers.AssuranceMapperImpl;
import nazih.youssef.backend.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private AssuranceMapperImpl dtoMapper;

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = dtoMapper.fromClientDTO(clientDTO);
        Client savedClient = clientRepository.save(client);
        return dtoMapper.fromClient(savedClient);
    }

    @Override
    public List<ClientDTO> listClients() {
        return clientRepository.findAll().stream()
                .map(c -> dtoMapper.fromClient(c))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClient(Long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return dtoMapper.fromClient(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) throws ClientNotFoundException {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        existing.setName(clientDTO.getName());
        existing.setEmail(clientDTO.getEmail());
        return dtoMapper.fromClient(clientRepository.save(existing));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}