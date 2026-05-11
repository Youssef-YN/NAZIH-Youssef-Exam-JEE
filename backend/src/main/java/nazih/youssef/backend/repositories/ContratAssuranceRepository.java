package nazih.youssef.backend.repositories;

import nazih.youssef.backend.entities.ContratAssurance;
import nazih.youssef.backend.enums.StatusContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {
    List<ContratAssurance> findByClientId(Long clientId);
    List<ContratAssurance> findByStatus(StatusContrat status);
}