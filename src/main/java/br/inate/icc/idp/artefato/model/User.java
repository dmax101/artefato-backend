package br.inate.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String bio;
    private Boolean isCrafter;
    private BigDecimal wallet;
    private List<Product> product;
    private List<Post> posts;
    private List<Post> timeline;

    public User() {
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

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Boolean isIsCrafter() {
        return this.isCrafter;
    }

    public Boolean getIsCrafter() {
        return this.isCrafter;
    }

    public void setIsCrafter(Boolean isCrafter) {
        this.isCrafter = isCrafter;
    }

    public BigDecimal getWallet() {
        return this.wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public List<Product> getProduct() {
        return this.product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getTimeline() {
        return this.timeline;
    }

    public void setTimeline(List<Post> timeline) {
        this.timeline = timeline;
    }    

}
