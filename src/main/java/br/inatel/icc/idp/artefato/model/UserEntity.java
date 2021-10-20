package br.inatel.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Data;

@Data
@Node("User")
public class UserEntity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Can't be null")
    @Size(min = 3)
    private final String name;

    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Can't be null")
    @Email
    private final String email;

    private final String bio;
    private final Boolean isCrafter;
    private final BigDecimal wallet;

    @Relationship(type = "CRAFTED", direction = Direction.OUTGOING)
    private List<ProductEntity> product;
    @Relationship(type = "POSTED", direction = Direction.OUTGOING)
    private List<PostEntity> posts;
    @Relationship(type = "LIKED", direction = Direction.OUTGOING)
    private List<PostEntity> liked;
    @Relationship(type = "FOLLOW", direction = Direction.OUTGOING)
    private List<FollowRelationship> follow;

    public UserEntity(String name, String email, String bio, Boolean isCrafter, BigDecimal wallet) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.isCrafter = isCrafter;
        this.wallet = wallet;
    }

    public UserEntity withId(UUID id) {
        if (id.equals(this.id)) {
            return this;
        } else {
            UserEntity newObject = new UserEntity(this.name, this.email, this.bio, this.isCrafter, this.wallet);
            newObject.id = id;
            return newObject;
        }
    }

}
