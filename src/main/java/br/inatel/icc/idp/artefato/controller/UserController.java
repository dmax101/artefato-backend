package br.inatel.icc.idp.artefato.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.BasicMessageDTO;
import br.inatel.icc.idp.artefato.model.DTO.FollowDTO;
import br.inatel.icc.idp.artefato.model.DTO.UserDTO;
import br.inatel.icc.idp.artefato.model.DTO.error.ErrorDTO;
import br.inatel.icc.idp.artefato.repository.UserRepository;
import br.inatel.icc.idp.artefato.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Environment env;

    @GetMapping
    public ResponseEntity<?> getUser(@Email(message = "Email deve ser bem formatado!") String email,
            @Size(min = 3, message = "Tamanho deve ser maior que 2 caracteres") String name,
            @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID") String id) {
        log.info("Getting users");

        if (email == null && name == null && id == null) {

            return ResponseEntity.ok(userRepository.getAllUsers());

        } else if (email != null && name != null && id != null) {

            Optional<UserEntity> responseUserEntity = userRepository.getUserByIdAndNameAndEmail(UUID.fromString(id),
                    name, email);

            if (responseUserEntity.isPresent()) {
                return ResponseEntity.ok(Arrays.asList(UserDTO.convertToDTO(responseUserEntity.get())));
            } else {
                return ResponseEntity.ok(Arrays.asList());
            }

        } else if (id != null) {

            Optional<UserEntity> responseUserEntity = userRepository.getUserById(UUID.fromString(id));

            if (responseUserEntity.isPresent()) {
                return ResponseEntity.ok(Arrays.asList(UserDTO.convertToDTO(responseUserEntity.get())));
            } else {
                return ResponseEntity.ok(Arrays.asList());
            }

        } else if (name != null && email != null) {
            Optional<UserEntity> responseUserEntity = userRepository.getUser(name, email);

            if (responseUserEntity.isPresent()) {
                return ResponseEntity.ok(Arrays.asList(UserDTO.convertToDTO(responseUserEntity.get())));
            } else {
                return ResponseEntity.ok(Arrays.asList());
            }
        } else if (name != null) {
            Collection<UserEntity> responseUserEntity = userRepository.getUserByName(name,
                    Integer.parseInt(env.getProperty("artefato.database.limit")));

            Collection<UserDTO> userDTOs = responseUserEntity.stream().map(UserDTO::convertToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(userDTOs);

        } else {
            Optional<UserEntity> responseUserEntity = userRepository.getUserByEmail(email);

            if (responseUserEntity.isPresent()) {
                return ResponseEntity.ok(Arrays.asList(UserDTO.convertToDTO(responseUserEntity.get())));
            } else {
                return ResponseEntity.ok(Arrays.asList());
            }
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserResource(
            @PathVariable @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID") String id) {

        Optional<UserEntity> responseUserEntity = userRepository.getUserById(UUID.fromString(id));

        if (responseUserEntity.isPresent()) {

            return ResponseEntity.ok(UserDTO.convertToDTO(responseUserEntity.get()));

        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuilder) {

        log.info("Object to send to Database: " + userDTO.toString());

        Optional<UserEntity> responseUserEntity = userService.saveUserToDatabase(userDTO.convetToEntity());

        if (responseUserEntity.isPresent()) {
            URI uri = uriBuilder.path("/user/{id}").buildAndExpand(responseUserEntity.get().getId()).toUri();

            return ResponseEntity.created(uri).body(UserDTO.convertToDTO(responseUserEntity.get()));
        }

        return ResponseEntity.badRequest().body(
                new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), "Something wrong is not right!", Arrays.asList()));

    }

    @PostMapping("/follow")
    public ResponseEntity<?> userFollow(@RequestBody @Valid FollowDTO followDTO, UriComponentsBuilder uriBuilder) {

        Optional<LocalDateTime> followSince = userRepository.createUserFollowRelationship(followDTO.getFollowerId(),
                followDTO.getInfluencerId());

        if (followSince.isPresent()) {

            URI uri = uriBuilder.path("/influencers/{id}").buildAndExpand(followDTO.getFollowerId()).toUri();

            String message = followDTO.getFollowerId() + " -- "
                    + followSince.get().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss")) + " -> "
                    + followDTO.getInfluencerId();

            log.info(message);

            return ResponseEntity.created(uri).body(new BasicMessageDTO(HttpStatus.CREATED.toString(), message));

        } else {

            return ResponseEntity.badRequest().body(new BasicMessageDTO(HttpStatus.BAD_REQUEST.toString(),
                    "You have to provide the follower and followed ids"));

        }
    }

    @GetMapping("/influencers/{id}")
    public ResponseEntity<?> getUserFollowResource(
            @PathVariable @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID") String id) {

        Optional<UserEntity> user = userRepository.getUserById(UUID.fromString(id));

        if (user.isPresent()) {
            Collection<UserEntity> responseUserEntity = userRepository.getUserFollowers(UUID.fromString(id));

            return ResponseEntity
                    .ok(responseUserEntity.stream().map(UserDTO::convertToDTO).collect(Collectors.toList()));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeUserAndAllAssets(
            @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Deve seguir o padrão de formatação UUID") String id) {

        Optional<String> message = userRepository.removeUserAndAllAssets(id);

        if (message.isPresent()) {

            return ResponseEntity.ok(new BasicMessageDTO(HttpStatus.OK.toString(),
                    "O usuário com o id " + message.get() + " foi removido junto com seus Posts e Products"));
        } else {

            return ResponseEntity.notFound().build();
        }
    }

}
