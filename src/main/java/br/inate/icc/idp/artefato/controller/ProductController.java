package br.inate.icc.idp.artefato.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inate.icc.idp.artefato.model.User;
import br.inate.icc.idp.artefato.model.error.BasicMessageDTO;
import br.inate.icc.idp.artefato.service.UserService;

@RestController
@RequestMapping("/")
public class ProductController {
    
    @Autowired
    UserService userService;
    
    @GetMapping
    @RequestMapping("/helloWorld")
    public ResponseEntity<BasicMessageDTO> helloWorld() {

        return ResponseEntity.ok().body(new BasicMessageDTO(
            HttpStatus.OK.toString(),
            "Hello World!"
        ));
    }

    @GetMapping
    @RequestMapping("/users")
    public Collection<User> getAll() {
        return userService.getAllUsers();
    }
}
