package nazih.youssef.backend;

import nazih.youssef.backend.entities.Client;
import nazih.youssef.backend.entities.ContratAssuranceSante;
import nazih.youssef.backend.entities.Paiement;
import nazih.youssef.backend.enums.NiveauCouverture;
import nazih.youssef.backend.enums.StatusContrat;
import nazih.youssef.backend.enums.TypePaiement;
import nazih.youssef.backend.repositories.ClientRepository;
import nazih.youssef.backend.repositories.ContratAssuranceRepository;
import nazih.youssef.backend.repositories.PaiementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, ContratAssuranceRepository contratAssuranceRepository, PaiementRepository paiementRepository) {
        return args -> Stream.of("Youssef NAZIH", "Abderrahmane HEDDAYA", "Mohamed Youssfi").forEach(name -> {
            Client client = new Client();
            client.setName(name);
            client.setEmail(name + "@gmail.com");
            clientRepository.save(client);

            ContratAssuranceSante contratAssuranceSante = new ContratAssuranceSante();
            contratAssuranceSante.setStatus(StatusContrat.VALIDE);
            contratAssuranceSante.setDateValidation(new Date());
            contratAssuranceSante.setMontantCotisation(1200);
            contratAssuranceSante.setTauxCouverture(5);
            contratAssuranceSante.setClient(client);
            contratAssuranceSante.setDureeContrat(12);
            contratAssuranceSante.setNiveauCouverture(NiveauCouverture.PREMIUM);
            contratAssuranceSante.setNombrePersonnesCouvertes(new Random().nextInt(5));
            contratAssuranceSante.setDateSouscription(new Date());
            contratAssuranceRepository.save(contratAssuranceSante);

            for (int i = 0; i < 5; i++){
                Paiement paiement = new Paiement();
                paiement.setDate(new Date());
                paiement.setType(TypePaiement.MENSUALITE);
                paiement.setMontant(new Random().nextDouble(1000, 10000));
                paiement.setContratAssurance(contratAssuranceSante);
                paiementRepository.save(paiement);
            }
        });
    }

}
