package nazih.youssef.backend.dtos;

import lombok.Data;
import nazih.youssef.backend.enums.StatusContrat;

import java.util.Date;

@Data
public class ContratAssuranceDTO {
    private String type;
    private Long id;
    private Date dateSouscription;
    private StatusContrat status;
    private Date dateValidation;
    private double montantCotisation;
    private int dureeContrat;
    private double tauxCouverture;
    private Long clientId;
}