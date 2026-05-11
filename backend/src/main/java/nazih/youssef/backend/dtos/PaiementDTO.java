package nazih.youssef.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nazih.youssef.backend.enums.TypePaiement;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementDTO {
    private Long id;
    private Date date;
    private double montant;
    private TypePaiement type;
    private Long contratId;
}