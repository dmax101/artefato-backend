package br.inatel.icc.idp.artefato.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import br.inatel.icc.idp.artefato.model.FollowRelationship;
import br.inatel.icc.idp.artefato.model.UserEntity;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, UUID> {

    @Async
    @Query("MATCH (u:User) RETURN u")
    Collection<UserEntity> getAllUsers();

    @Async
    @Query("MATCH (u:User{name: $name, email: $email}) RETURN u LIMIT 1")
    Optional<UserEntity> getUser(String name, String email);

    @Async
    @Query("MATCH (:User{name: 'Danilo'})-[:FOLLOW]->(u:User) RETURN u")
    Collection<UserEntity> getAllUserFollowed();

    @Async
    @Query("CREATE (u:User {id: randomUUID(), name: $name, email: $email, bio: $bio, isCrafter: $isCrafter, wallet: $wallet}) RETURN u")
    Optional<UserEntity> saveUserToDatabase(String name, String email, String bio, Boolean isCrafter,
            BigDecimal wallet);

    @Async
    @Query("MATCH (U1:User {id: $followerId}) MATCH (U2:User {id: $followedId}) CREATE (U1)-[F:FOLLOW{since: localdatetime()}]->(U2) RETURN F")
    Optional<FollowRelationship> createUserFollowRelationship(UUID followerId, UUID followedId);

    @Async
    @Query("MATCH (u:User{id: $id}) RETURN u LIMIT 1")
    Optional<UserEntity> getUserById(UUID id);

    @Async
    @Query("MATCH (u:User{email: $email}) RETURN u LIMIT 1")
    Optional<UserEntity> getUserByEmail(String email);

    @Async
    @Query("MATCH (u:User{name: $name}) RETURN u LIMIT $limit")
    Collection<UserEntity> getUserByName(String name, int limit);

    @Async
    @Query("MATCH (u:User{id: $id, name: $name, email: $email}) RETURN u LIMIT 1")
    Optional<UserEntity> getUserByIdAndNameAndEmail(UUID id, String name, String email);
}