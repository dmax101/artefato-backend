package br.inate.icc.idp.artefato.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import br.inate.icc.idp.artefato.model.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Async
    @Query("MATCH (u:User) RETURN u")
    Collection<User> getAllUsers();
    
    @Async
    @Query("MATCH (:User{name: 'Danilo'})-[:FOLLOW]->(u:User) RETURN u")
    Collection<User> getAllUserFollowed();
    
    @Async
    Optional<User> findByNameIgnoreCase(String string);
    
    @Async
    Optional<User> findById(Long id);
    
}