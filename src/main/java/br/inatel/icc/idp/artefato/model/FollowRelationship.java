package br.inatel.icc.idp.artefato.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.Data;

@Data
@RelationshipProperties
public class FollowRelationship {

    @Id
    @GeneratedValue
    private Long id;

    private final LocalDateTime since;

    private final List<String> followers;

    @TargetNode
    private final UserEntity user;

    public FollowRelationship(UserEntity user, List<String> followers, LocalDateTime since) {
        this.user = user;
        this.followers = followers;
        this.since = since;
    }

    public List<String> getFollowers() {
        return followers;
    }

}
