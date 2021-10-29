package br.inatel.icc.idp.artefato.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import br.inatel.icc.idp.artefato.model.ProductEntity;

@Repository
public interface ProductRepository extends Neo4jRepository<ProductEntity, UUID> {

    @Async
    @Query("MATCH (p:Product) RETURN p")
    Collection<ProductEntity> getAllProducts();

    @Async
    @Query("MATCH (p:Product) WHERE toLower(p.name) CONTAINS toLower($name) RETURN p")
    Collection<ProductEntity> getProductsByName(String name);

    @Async
    @Query("MATCH (p:Product{id: $id}) WHERE toLower(p.name) CONTAINS toLower($name) RETURN p")
    Optional<ProductEntity> getProductByIdAndName(UUID id, String name);

    @Async
    @Query("MATCH (p:Product{id: $id}) RETURN p")
    Optional<ProductEntity> getProductById(UUID id);

    @Async
    @Query("MATCH (u:User{id: $idCrafter}) CREATE (p:Product {id: randomUUID(), name: $name, description: $description, price: $price, isAvailable: $isAvailable, imageURL: $imageURL}) CREATE (u)-[:CRAFTED{id: randomUUID(), since: localdatetime({timezone: 'America/Sao Paulo'})}]->(p) RETURN p;")
    Optional<ProductEntity> createNewProduct(UUID idCrafter, String name, String description, BigDecimal price,
            Boolean isAvailable, List<String> imageURL);

    @Async
    @Query("MATCH (p:Product{id: $id}) SET p.isAvailable = $avaiability RETURN p.isAvailable")
    Optional<Boolean> setProductAvaiability(UUID id, Boolean avaiability);

}