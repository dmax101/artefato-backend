package br.inatel.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Data;

@Data
@Node("Product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private final Long id;

    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Boolean isAvailable;
    private final List<URL> imageURL;

    public ProductEntity withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            return new ProductEntity(id, this.name, this.description, this.price, this.isAvailable, this.imageURL);
        }
    }

}