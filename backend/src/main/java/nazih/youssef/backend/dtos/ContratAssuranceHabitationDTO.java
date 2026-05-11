package nazih.youssef.backend.dtos;

import lombok.Data;
import nazih.youssef.backend.enums.TypeLogement;

@Data
public class ContratAssuranceHabitationDTO extends ContratAssuranceDTO {
    private TypeLogement typeLogement;
    private String adresseLogement;
    private double superficie;
}