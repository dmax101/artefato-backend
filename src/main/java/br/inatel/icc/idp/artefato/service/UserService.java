package br.inatel.icc.idp.artefato.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Validated
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Collection<UserEntity> getAllUsers() {
        log.info("Get all users");
        return userRepository.getAllUsers();
    }

    public Optional<UserEntity> saveUserToDatabase(@Valid UserEntity userEntity) {

        return userRepository.saveUserToDatabase(userEntity.getName(), userEntity.getEmail(), userEntity.getBio(),
                userEntity.getIsCrafter(), userEntity.getWallet());

    }

}
