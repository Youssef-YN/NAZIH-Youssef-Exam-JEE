package nazih.youssef.backend.repositories;

import nazih.youssef.backend.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> { }
