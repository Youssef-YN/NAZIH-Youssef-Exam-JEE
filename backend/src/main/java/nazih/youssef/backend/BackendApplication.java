package nazih.youssef.backend;

import lombok.AllArgsConstructor;
import nazih.youssef.backend.dtos.ClientDTO;
import nazih.youssef.backend.dtos.ContratAssuranceSanteDTO;
import nazih.youssef.backend.dtos.PaiementDTO;
import nazih.youssef.backend.entities.Client;
import nazih.youssef.backend.entities.ContratAssuranceSante;
import nazih.youssef.backend.entities.Paiement;
import nazih.youssef.backend.enums.NiveauCouverture;
import nazih.youssef.backend.enums.StatusContrat;
import nazih.youssef.backend.enums.TypePaiement;
import nazih.youssef.backend.exceptions.ClientNotFoundException;
import nazih.youssef.backend.exceptions.ContratNotFoundException;
import nazih.youssef.backend.mappers.AssuranceMapperImpl;
import nazih.youssef.backend.services.ClientService;
import nazih.youssef.backend.services.ContratAssuranceService;
import nazih.youssef.backend.services.PaiementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
    private AssuranceMapperImpl dtoMapper;
    @Bean
    CommandLineRunner commandLineRunner(ClientService clientService, ContratAssuranceService contratAssuranceService, PaiementService paiementService) {
        return args -> Stream.of("Youssef NAZIH", "Abderrahmane HEDDAYA", "Mohamed Youssfi", "Ahmed REBBANI").forEach(name -> {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setName(name);
            clientDTO.setEmail(name + "@gmail.com");
            clientDTO = clientService.saveClient(clientDTO);

            ContratAssuranceSanteDTO contratAssuranceSanteDTO = new ContratAssuranceSanteDTO();
            contratAssuranceSanteDTO.setStatus(StatusContrat.VALIDE);
            contratAssuranceSanteDTO.setDateValidation(new Date());
            contratAssuranceSanteDTO.setMontantCotisation(1200);
            contratAssuranceSanteDTO.setTauxCouverture(5);
            contratAssuranceSanteDTO.setClientId(clientDTO.getId());
            contratAssuranceSanteDTO.setDureeContrat(12);
            contratAssuranceSanteDTO.setNiveauCouverture(NiveauCouverture.PREMIUM);
            contratAssuranceSanteDTO.setNombrePersonnesCouvertes(new Random().nextInt(5) + 1);
            contratAssuranceSanteDTO.setDateSouscription(new Date());
            try {
                contratAssuranceSanteDTO = contratAssuranceService.saveContratSante(contratAssuranceSanteDTO);
            } catch (ClientNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < 5; i++){
                PaiementDTO paiementDTO = new PaiementDTO();
                paiementDTO.setDate(new Date());
                paiementDTO.setType(TypePaiement.MENSUALITE);
                paiementDTO.setMontant(new Random().nextDouble(1000, 10000));
                paiementDTO.setContratId(contratAssuranceSanteDTO.getId());
                try {
                    paiementService.savePaiement(paiementDTO);
                } catch (ContratNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
