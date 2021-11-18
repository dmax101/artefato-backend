package br.inatel.icc.idp.artefato.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

    private final String name;
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
    private List<UserEntity> follow;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.name.equals(((UserEntity) obj).name) && this.email.equals(((UserEntity) obj).email)
                && this.bio.equals(((UserEntity) obj).bio) && this.isCrafter.equals(((UserEntity) obj).isCrafter)
                && this.wallet.equals(((UserEntity) obj).wallet)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 97 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 97 * hash + (this.bio != null ? this.bio.hashCode() : 0);
        hash = 97 * hash + (this.isCrafter ? 1 : 0);
        hash = 97 * hash + (this.wallet != null ? this.wallet.hashCode() : 0);
        return hash;
    }
}
