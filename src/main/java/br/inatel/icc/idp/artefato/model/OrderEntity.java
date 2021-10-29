package br.inatel.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.Pattern;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Node("Order")
public class OrderEntity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private UUID id;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID")
    private UUID buyerId;
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID")
    private UUID productId;
    private String buyerName;
    private BigDecimal value;
    private String productName;
    private ResponsePixAe paymentInfo;
    private String status;

}
