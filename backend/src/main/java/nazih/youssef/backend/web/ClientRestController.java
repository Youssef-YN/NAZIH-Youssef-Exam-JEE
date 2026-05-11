package nazih.youssef.backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nazih.youssef.backend.dtos.ClientDTO;
import nazih.youssef.backend.exceptions.ClientNotFoundException;
import nazih.youssef.backend.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ClientRestController {
    private ClientService clientService;
    @GetMapping("/clients")
    public List<ClientDTO> clients() {
        return clientService.listClients();
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable(name = "id") Long clientId) throws ClientNotFoundException {
        return clientService.getClient(clientId);
    }
    @PostMapping("/clients")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }
    @PutMapping("/clients/{clientId}")
    public ClientDTO updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDTO) throws ClientNotFoundException {
        return clientService.updateClient(clientId, clientDTO);
    }
    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}