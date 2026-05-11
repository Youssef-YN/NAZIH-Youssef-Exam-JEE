package nazih.youssef.backend.dtos;

import lombok.Data;

@Data
public class ContratAssuranceAutomobileDTO extends ContratAssuranceDTO {
    private String immatriculationVehicule;
    private String marqueVehicule;
    private String modeleVehicule;
}