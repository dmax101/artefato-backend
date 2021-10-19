package br.inatel.icc.idp.artefato.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class FollowRelationship {

    @Id
    @GeneratedValue
    private Long id;

    private final List<String> followers;

    @TargetNode
    private final UserEntity user;

    public FollowRelationship(UserEntity user, List<String> followers) {
        this.user = user;
        this.followers = followers;
    }

    public List<String> getFollowers() {
        return followers;
    }

}
