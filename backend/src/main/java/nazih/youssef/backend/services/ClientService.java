package nazih.youssef.backend.services;

import nazih.youssef.backend.dtos.ClientDTO;
import nazih.youssef.backend.exceptions.ClientNotFoundException;

import java.util.List;

public interface ClientService {
    ClientDTO saveClient(ClientDTO clientDTO);
    List<ClientDTO> listClients();
    ClientDTO getClient(Long id) throws ClientNotFoundException;
    ClientDTO updateClient(Long id, ClientDTO clientDTO) throws ClientNotFoundException;
    void deleteClient(Long id);
}