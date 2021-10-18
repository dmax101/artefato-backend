package br.inate.icc.idp.artefato.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inate.icc.idp.artefato.model.ProductEntity;
import br.inate.icc.idp.artefato.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public Collection<ProductEntity> getAllProducts() {
        return productService.getProductCraftedBy("Danilo");
    }
}
