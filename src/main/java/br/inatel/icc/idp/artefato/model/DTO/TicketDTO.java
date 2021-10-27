package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;
import java.util.UUID;

import br.inatel.icc.idp.artefato.model.ResponsePixAe;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {

    private String buyerName;
    private UUID buyerId;
    private BigDecimal value;
    private String productName;
    private UUID productId;
    private ResponsePixAe paymentInfo;

}
