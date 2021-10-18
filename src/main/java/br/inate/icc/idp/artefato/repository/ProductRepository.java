package br.inate.icc.idp.artefato.repository;

import java.util.Collection;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import br.inate.icc.idp.artefato.model.ProductEntity;

@Repository
public interface ProductRepository extends Neo4jRepository<ProductEntity, Long> {

    @Query("MATCH (p:Product) return p")
    Collection<ProductEntity> getAllProducts();

    @Query("MATCH (p:Product)<-[:CRAFTED]-(u:User{name: $name}) return p")
    Collection<ProductEntity> getProductCraftedBy(String name);

}
