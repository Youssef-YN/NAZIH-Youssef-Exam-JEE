package nazih.youssef.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
    private TypeLogement typeLogement;
    private String adresseLogement;
    private String superficie;
}
