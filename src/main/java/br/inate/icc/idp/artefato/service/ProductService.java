package br.inate.icc.idp.artefato.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inate.icc.idp.artefato.model.ProductEntity;
import br.inate.icc.idp.artefato.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public Collection<ProductEntity> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Collection<ProductEntity> getProductCraftedBy(String name) {
        return productRepository.getProductCraftedBy(name);
    }
}
