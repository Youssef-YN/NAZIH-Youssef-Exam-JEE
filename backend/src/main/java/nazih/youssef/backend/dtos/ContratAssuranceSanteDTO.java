package nazih.youssef.backend.dtos;

import lombok.Data;
import nazih.youssef.backend.enums.NiveauCouverture;

@Data
public class ContratAssuranceSanteDTO extends ContratAssuranceDTO {
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}