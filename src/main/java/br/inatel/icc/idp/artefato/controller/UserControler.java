package br.inatel.icc.idp.artefato.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.UserDTO;
import br.inatel.icc.idp.artefato.model.DTO.error.ErrorDTO;
import br.inatel.icc.idp.artefato.repository.UserRepository;
import br.inatel.icc.idp.artefato.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Cacheable("UserCache")
@Slf4j
public class UserControler {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Collection<UserEntity> getAllUsers() {
        log.info("Get all users");
        return userRepository.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserDTO userDTO, UriComponentsBuilder uriBuilder) {

        UserDTO responseUserDTO = userService.saveToDatabase(userDTO);

        if (responseUserDTO != null) {

            URI uri = uriBuilder.path("/users/{id}").buildAndExpand(userDTO.getId()).toUri();

            return ResponseEntity.created(uri).body(responseUserDTO);
        }

        return ResponseEntity.badRequest().body(
                new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), "Something wrong is not right!", Arrays.asList()));

    }

}
