package br.inatel.icc.idp.artefato.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import br.inatel.icc.idp.artefato.repository.ProductRepository;
import br.inatel.icc.idp.artefato.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Cacheable
@Validated
@Slf4j
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProduct(String id, String name) {
        log.info("Searching products");

        if (id == null && name == null) {

            Collection<ProductEntity> responseProductEntities = productRepository.getAllProducts();

            return ResponseEntity.ok().body(productService.convertListEntityToListDTO(responseProductEntities));

        } else if (id != null && name != null) {

            Optional<ProductEntity> productEntity = productRepository.getProductByIdAndName(UUID.fromString(id), name);

            if (productEntity.isPresent()) {
                return ResponseEntity.ok(Arrays.asList(productEntity.get()));
            } else {
                return ResponseEntity.ok(Arrays.asList());
            }

        } else if (id != null) {

            Optional<ProductEntity> productEntity = productRepository.findById(UUID.fromString(id));

            if (productEntity.isPresent()) {

                return ResponseEntity.ok(Arrays.asList(productEntity.get()));

            } else {

                return ResponseEntity.ok(Arrays.asList());

            }

        } else {
            Collection<ProductEntity> productEntity = productRepository.getProductsByName(name);

            return ResponseEntity.ok(productService.convertListEntityToListDTO(productEntity));
        }

    }

}
