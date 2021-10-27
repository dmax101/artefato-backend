package br.inatel.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Data;

@Data
@Node("Product")
public class ProductEntity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private UUID id;

    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Boolean isAvailable;
    private final List<String> imageURL;

    public ProductEntity(String name, String description, BigDecimal price, Boolean isAvailable,
            List<String> imageURL) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imageURL = imageURL;
    }

    public ProductEntity withId(UUID id) {
        if (id.equals(this.id)) {
            return this;
        } else {
            ProductEntity newObject = new ProductEntity(this.name, this.description, this.price, this.isAvailable,
                    this.imageURL);
            newObject.id = id;
            return newObject;
        }
    }
}