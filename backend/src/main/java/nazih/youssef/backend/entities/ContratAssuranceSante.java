package nazih.youssef.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nazih.youssef.backend.enums.NiveauCouverture;

@Entity
@DiscriminatorValue("SANTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratAssuranceSante extends  ContratAssurance{
    private NiveauCouverture niveauCouverture;
    private Long nombrePersonnesCouvertes;
}
