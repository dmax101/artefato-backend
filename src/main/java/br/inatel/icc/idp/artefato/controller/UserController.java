package br.inatel.icc.idp.artefato.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.BasicMessageDTO;
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
    public ResponseEntity<?> getUser(String email, String name, String id) {
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
    public ResponseEntity<?> getUserResource(@PathVariable String id) {

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

            return ResponseEntity.created(uri).body(Arrays.asList(UserDTO.convertToDTO(responseUserEntity.get())));
        }

        return ResponseEntity.badRequest().body(
                new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), "Something wrong is not right!", Arrays.asList()));

    }

    @PostMapping("/follow")
    public ResponseEntity<?> userFollow(String followerId, String influencerId, UriComponentsBuilder uriBuilder) {

        Optional<LocalDateTime> followSince = userRepository.createUserFollowRelationship(UUID.fromString(followerId),
                UUID.fromString(influencerId));

        if (followSince.isPresent()) {

            URI uri = uriBuilder.path("/followers/{id}").buildAndExpand(followerId).toUri();

            String message = followerId + " -- "
                    + followSince.get().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss")) + " -> "
                    + influencerId;

            log.info(message);

            return ResponseEntity.created(uri).body(new BasicMessageDTO(HttpStatus.CREATED.toString(), message));

        } else {

            return ResponseEntity.badRequest().body(new BasicMessageDTO(HttpStatus.BAD_REQUEST.toString(),
                    "You have to provide the follower and followed ids"));

        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeUserAndAllAssets(String id) {

        Optional<String> message = userRepository.removeUserAndAllAssets(id);

        if (message.isPresent()) {

            return ResponseEntity.ok(new BasicMessageDTO(HttpStatus.OK.toString(),
                    "The user with id " + message.get() + " was removed with all Posts and Products"));
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.error(HttpStatus.BAD_REQUEST.toString(), errors);

        return errors;
    }

}
