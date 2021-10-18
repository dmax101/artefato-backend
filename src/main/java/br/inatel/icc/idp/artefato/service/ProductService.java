package br.inatel.icc.idp.artefato.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import br.inatel.icc.idp.artefato.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public Collection<ProductEntity> getAllProducts() {
        log.info("Get all products");
        return productRepository.getAllProducts();
    }
    
    public Collection<ProductEntity> getProductCraftedBy(String name) {
        log.info("Get all products by crafter");
        return productRepository.getProductCraftedBy(name);
    }
}
