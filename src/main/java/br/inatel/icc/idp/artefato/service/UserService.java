package br.inatel.icc.idp.artefato.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.UserDTO;
import br.inatel.icc.idp.artefato.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Collection<UserEntity> getAllUsers() {
        log.info("Get all users");
        return userRepository.getAllUsers();
    }

    public Optional<UserEntity> saveUserToDatabase(UserDTO userDTO) {

        return userRepository.saveUserToDatabase(userDTO.getName(), userDTO.getEmail(), userDTO.getBio(),
                userDTO.getIsCrafter(), userDTO.getWallet());

    }

}
