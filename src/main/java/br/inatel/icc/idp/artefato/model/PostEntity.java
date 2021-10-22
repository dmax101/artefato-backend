package br.inatel.icc.idp.artefato.model;

import java.math.BigInteger;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Data;

@Data
@Node("Post")
public class PostEntity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private final UUID id;

    private final String title;
    private final String description;
    private final BigInteger like;
    private final BigInteger share;
    private final Boolean isArchived;

}
