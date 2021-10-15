package br.inate.icc.idp.artefato.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import br.inate.icc.idp.artefato.model.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, UUID> {

    @Query("MATCH (u:User) RETURN u")
    Collection<User> getAllUsers();
    
}
