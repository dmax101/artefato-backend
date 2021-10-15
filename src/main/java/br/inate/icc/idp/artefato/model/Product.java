package br.inate.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Data;

@Data
@Node
public class Product {
    
    // @NonNull
    // @NotNull(message = "Id must to be valid UUID")
    @Id
    private UUID id;
    // @NotNull(message = "You have to provide a name")
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
    private List<URL> imageURL;

}
