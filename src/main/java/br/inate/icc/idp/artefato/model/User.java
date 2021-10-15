package br.inate.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Data;

@Data
@Node
public class User {

    // @NonNull
    // @NotNull(message = "Id must to be valid UUID")
    @Id
    private UUID id;
    // @NotNull(message = "You have to provide a name")
    private String name;
    // @NotNull(message = "You have to provide an e-mail")
    private String email;
    private String bio;
    private Boolean isCrafter;
    private BigDecimal wallet;
    @Relationship(type = "CRAFTED", direction = Relationship.Direction.OUTGOING)
    private List<Product> product;
    @Relationship(type = "POSTED", direction = Relationship.Direction.OUTGOING)
    private List<Post> posts;
    @Relationship(type = "LIKED", direction = Relationship.Direction.OUTGOING)
    private List<Post> liked;
    @Relationship(type = "FOLLOW", direction = Relationship.Direction.OUTGOING)
    private List<User> follow;

}
