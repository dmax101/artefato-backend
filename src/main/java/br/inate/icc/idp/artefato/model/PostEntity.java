package br.inate.icc.idp.artefato.model;

import java.math.BigInteger;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Data;

@Data
@Node("Post")
public class PostEntity {

    @Id @GeneratedValue
    private final Long id;
    
    private final String title;
    private final String description;
    private final BigInteger like;
    private final BigInteger share;
    private final Boolean isArchived;

    public PostEntity withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            return new PostEntity(id, this.title, this.description, this.like, this.share, this.isArchived);
        }
    }

}
