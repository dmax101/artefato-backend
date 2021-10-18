package br.inate.icc.idp.artefato.repository;

import java.util.Collection;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import br.inate.icc.idp.artefato.model.PostEntity;

public interface PostRepository extends Neo4jRepository<PostEntity, Long> {

    @Query("MATCH (p:Post) return p")
    Collection<PostEntity> getAllPosts();
}
