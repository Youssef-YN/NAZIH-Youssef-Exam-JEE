package nazih.youssef.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}
