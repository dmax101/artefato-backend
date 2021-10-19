package br.inatel.icc.idp.artefato.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import br.inatel.icc.idp.artefato.model.UserEntity;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, UUID> {

    @Async
    @Query("MATCH (u:User) RETURN u")
    Collection<UserEntity> getAllUsers();

    @Async
    @Query("MATCH (:User{name: 'Danilo'})-[:FOLLOW]->(u:User) RETURN u")
    Collection<UserEntity> getAllUserFollowed();

    @Async
    Optional<UserEntity> findByNameIgnoreCase(String string);

    @Async
    Optional<UserEntity> findById(Long id);

    @Async
    @Query("CREATE (u:User {id: randomUUID(), name: $name, email: $email, bio: $bio, isCrafter: $isCrafter, wallet: $wallet}) RETURN u")
    Optional<UserEntity> saveUserToDatabase(String name, String email, String bio, Boolean isCrafter,
            BigDecimal wallet);

}