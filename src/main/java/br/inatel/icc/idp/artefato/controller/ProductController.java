package br.inatel.icc.idp.artefato.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import br.inatel.icc.idp.artefato.model.DTO.BasicMessageDTO;
import br.inatel.icc.idp.artefato.model.DTO.NewProductDTO;
import br.inatel.icc.idp.artefato.model.DTO.OrderDTO;
import br.inatel.icc.idp.artefato.model.DTO.ProductDTO;
import br.inatel.icc.idp.artefato.model.DTO.TicketDTO;
import br.inatel.icc.idp.artefato.repository.ProductRepository;
import br.inatel.icc.idp.artefato.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Validated
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Environment env;

    @GetMapping
    public ResponseEntity<?> getProduct(
            @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID") String id,
            @Size(min = 3, message = "Tamanho deve ser maior que 2 caracteres") String name) {
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

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseProduct(@RequestBody @Valid OrderDTO order) {

        Optional<ProductEntity> product = productRepository.getProductById(order.getProductId());

        if (product.isEmpty() || Boolean.TRUE.equals(!product.get().getIsAvailable())) {

            return ResponseEntity.ok(new BasicMessageDTO(HttpStatus.OK.toString(), "O produto não está disponível!"));
        }

        Pair<TicketDTO, BasicMessageDTO> paymentInfo = productService.getPaymentInfo(order);

        if (paymentInfo.getFirst().getPaymentInfo() == null) {
            return ResponseEntity.badRequest().body(paymentInfo.getSecond());
        }

        return ResponseEntity.ok(paymentInfo.getFirst());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid NewProductDTO newProductDTO,
            UriComponentsBuilder uriBuilder) {

        Optional<ProductEntity> product = productService.createNewProduct(newProductDTO);

        if (product.isPresent()) {
            URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.get().getId()).toUri();

            return ResponseEntity.created(uri).body(ProductDTO.convertToDTO(product.get()));

        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductResource(
            @PathVariable @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID") String id) {

        Optional<ProductEntity> responseProductEntity = productRepository.getProductById(UUID.fromString(id));

        if (responseProductEntity.isPresent()) {

            return ResponseEntity.ok(ProductDTO.convertToDTO(responseProductEntity.get()));

        }

        return ResponseEntity.notFound().build();

    }

}
