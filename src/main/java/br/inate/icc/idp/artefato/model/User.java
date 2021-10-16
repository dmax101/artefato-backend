package br.inate.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import lombok.Data;

@Data
@Node("User")
public class User {

    @Id
    @GeneratedValue
    private final Long id;

    private final String name;
    private final String email;
    private final String bio;
    private final Boolean isCrafter;
    private final BigDecimal wallet;
    @Relationship(type = "CRAFTED", direction = Direction.OUTGOING)
    private List<Product> product;
    @Relationship(type = "POSTED", direction = Direction.OUTGOING)
    private List<Post> posts;
    @Relationship(type = "LIKED", direction = Direction.OUTGOING)
    private List<Post> liked;
    @Relationship(type = "FOLLOW", direction = Direction.OUTGOING)
    private List<User> follow;

    public User withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            return new User(id, this.name, this.email, this.bio, this.isCrafter, this.wallet);
        }
    }

}
