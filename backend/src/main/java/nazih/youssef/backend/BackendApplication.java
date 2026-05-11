package nazih.youssef.backend;

import nazih.youssef.backend.entities.Client;
import nazih.youssef.backend.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
        return args -> Stream.of("Youssef NAZIH", "Abderrahmane HEDDAYA", "Mohamed Youssfi").forEach(name -> {
            Client client = new Client();
            client.setName(name);
            client.setEmail(name + "@gmail.com");
            clientRepository.save(client);
        });
    }

}
