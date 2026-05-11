package nazih.youssef.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nazih.youssef.backend.enums.TypePaiement;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paiement {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double montant;
    private TypePaiement type;
}
