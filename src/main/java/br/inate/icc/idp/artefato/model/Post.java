package br.inate.icc.idp.artefato.model;

import java.math.BigInteger;
import java.util.UUID;

public class Post {
    
    private UUID id;
    private String title;
    private String description;
    private BigInteger like;
    private BigInteger comment;
    private BigInteger share;
    private Boolean isArchived;

    public Post() {
      // TODO document why this constructor is empty
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getLike() {
        return this.like;
    }

    public void setLike(BigInteger like) {
        this.like = like;
    }

    public BigInteger getComment() {
        return this.comment;
    }

    public void setComment(BigInteger comment) {
        this.comment = comment;
    }

    public BigInteger getShare() {
        return this.share;
    }

    public void setShare(BigInteger share) {
        this.share = share;
    }

    public Boolean isIsArchived() {
        return this.isArchived;
    }

    public Boolean getIsArchived() {
        return this.isArchived;
    }

    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }
    
}
