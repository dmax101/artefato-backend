package br.inate.icc.idp.artefato.repository;

import java.util.Collection;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import br.inate.icc.idp.artefato.model.Product;

@Repository
public interface ProductRepository extends Neo4jRepository<Product, Long> {

    @Query("MATCH (p:Product) return p")
    Collection<Product> getAllProducts();

}
