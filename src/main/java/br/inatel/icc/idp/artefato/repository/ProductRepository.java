package br.inatel.icc.idp.artefato.repository;

import java.util.Collection;
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

}
