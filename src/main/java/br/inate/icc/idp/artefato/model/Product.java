package br.inate.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class Product {
    
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
    private List<URL> imageURL;


    public Product() {
      // TODO document why this constructor is empty
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isIsAvailable() {
        return this.isAvailable;
    }

    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public List<URL> getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(List<URL> imageURL) {
        this.imageURL = imageURL;
    }

}
