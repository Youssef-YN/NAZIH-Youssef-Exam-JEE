package nazih.youssef.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("AUTO")
@Data @AllArgsConstructor @NoArgsConstructor
public class ContratAssuranceAutomobile extends ContratAssurance {
    private String immatriculationVehicule;
    private String marqueVehicule;
    private String modeleVehicule;
}
