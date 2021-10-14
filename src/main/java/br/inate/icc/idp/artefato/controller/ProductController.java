package br.inate.icc.idp.artefato.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inate.icc.idp.artefato.model.error.BasicMessageDTO;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @GetMapping
    public ResponseEntity<BasicMessageDTO> helloWorld() {

        return ResponseEntity.ok().body(new BasicMessageDTO(
            HttpStatus.OK.toString(),
            "Hello World!"
        ));
    }

}
