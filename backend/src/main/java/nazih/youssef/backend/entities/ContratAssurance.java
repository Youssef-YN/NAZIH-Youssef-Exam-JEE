package nazih.youssef.backend.entities;
// 72
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nazih.youssef.backend.enums.StatusContrat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 5)
public abstract class ContratAssurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateSouscription;
    @Enumerated(EnumType.STRING)
    private StatusContrat status;
    private Date dateValidation;
    private double montantCotisation;
    private int dureeContrat; // Duree en mois Monsieur
    private double tauxCouverture;

    @OneToMany(mappedBy = "contratAssurance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Paiement> paiements;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;
}
