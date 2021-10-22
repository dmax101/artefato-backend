package br.inatel.icc.idp.artefato.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import br.inatel.icc.idp.artefato.model.ResponsePixAe;
import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.BasicMessageDTO;
import br.inatel.icc.idp.artefato.model.DTO.OrderDTO;
import br.inatel.icc.idp.artefato.model.DTO.ProductDTO;
import br.inatel.icc.idp.artefato.model.DTO.error.ErrorDTO;
import br.inatel.icc.idp.artefato.repository.ProductRepository;
import br.inatel.icc.idp.artefato.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    Environment env;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    enum ERRORS_STATUS {
        FOUND, NOT_FOUND
    }

    public Collection<ProductDTO> convertListEntityToListDTO(Collection<ProductEntity> responseProductEntities) {

        return responseProductEntities.stream().map(ProductDTO::convertToDTO).collect(Collectors.toList());

    }

    public BasicMessageDTO purchase(OrderDTO order) {

        Optional<UserEntity> buyer = userRepository.getUserById(order.getBuyerId());
        Optional<UserEntity> crafter = userRepository.getUserCrafterOfProduct(order.getProductId());
        Optional<ProductEntity> product = productRepository.getProductById(order.getProductId());

        if (buyer.isEmpty() || crafter.isEmpty() || product.isEmpty()) {
            Map<String, String> error = new HashMap<String, String>();

            String buyerError = buyer.isEmpty() ? ERRORS_STATUS.NOT_FOUND.toString() : ERRORS_STATUS.FOUND.toString();
            String crafterError = crafter.isEmpty() ? ERRORS_STATUS.NOT_FOUND.toString()
                    : ERRORS_STATUS.FOUND.toString();
            String productError = product.isEmpty() ? ERRORS_STATUS.NOT_FOUND.toString()
                    : ERRORS_STATUS.FOUND.toString();

            error.put("buyer", buyerError);
            error.put("crafter", crafterError);
            error.put("product", productError);

            return new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), "Não foi encontrado", Arrays.asList(error));
        }

        String chave = crafter.get().getEmail();
        String tipo = "email";
        String nome = crafter.get().getName();
        String info = "Compra de: " + product.get().getName() + ": " + product.get().getDescription();
        BigDecimal valor = product.get().getPrice();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(env.getProperty("artefato.consuming.url"))
                .queryParam("chave", chave).queryParam("tipo", tipo).queryParam("nome", nome).queryParam("info", info)
                .queryParam("valor", valor.toString());

        log.info(builder.toString());

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        ResponseEntity<ResponsePixAe> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                new HttpEntity<>(new ResponsePixAe(null, null, null, null, null), createJSONHeader()),
                ResponsePixAe.class);

        log.info(exchange.getBody().toString());

        return null;
    }

    private static HttpHeaders createJSONHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

}
