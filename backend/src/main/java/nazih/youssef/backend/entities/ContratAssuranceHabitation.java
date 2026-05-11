package nazih.youssef.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nazih.youssef.backend.enums.TypeLogement;

@Entity
@DiscriminatorValue("HABIT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratAssuranceHabitation extends ContratAssurance{
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    private String adresseLogement;
    private double superficie;
}
