package br.inate.icc.idp.artefato.model;

import java.math.BigInteger;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Data;

@Data
@Node
public class Post {
    
    // @NonNull
    // @NotNull(message = "Id must to be valid UUID")
    @Id
    private UUID id;
    // @NonNull
    // @NotNull(message = "You have to provide a title")
    private String title;
    private String description;
    private BigInteger like;
    private BigInteger share;
    private Boolean isArchived;

}
