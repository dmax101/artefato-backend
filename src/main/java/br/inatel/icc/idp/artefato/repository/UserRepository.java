package br.inatel.icc.idp.artefato.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Query("MATCH (u:User{email: $email}) WHERE toLower(u.name) CONTAINS toLower($name) RETURN u LIMIT 1")
    Optional<UserEntity> getUser(String name, String email);

    @Async
    @Query("CREATE (u:User {id: randomUUID(), name: $name, email: $email, bio: $bio, isCrafter: $isCrafter, wallet: $wallet}) RETURN u")
    Optional<UserEntity> saveUserToDatabase(String name, String email, String bio, Boolean isCrafter,
            BigDecimal wallet);

    @Async
    @Query("MATCH (u1:User {id: $followerId}) MATCH (u2:User {id: $influencerId}) CREATE (u1)-[f:FOLLOW{since: localdatetime({timezone: 'America/Sao Paulo'})}]->(u2) RETURN f.since")
    Optional<LocalDateTime> createUserFollowRelationship(UUID followerId, UUID influencerId);

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

    @Async
    @Query("MATCH (user:User{id: $userId}) OPTIONAL MATCH (user)-[c:CRAFTED]->(product:Product) OPTIONAL MATCH (user)-[p:POSTED]->(post:Post) DETACH DELETE user, product, post RETURN $userId LIMIT 1")
    Optional<String> removeUserAndAllAssets(String userId);

    @Async
    @Query("MATCH (user:User)-[c:CRAFTED]->(product:Product{id: $productId}) RETURN user")
    Optional<UserEntity> getUserCrafterOfProduct(UUID productId);
}