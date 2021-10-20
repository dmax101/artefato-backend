package br.inatel.icc.idp.artefato.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.inatel.icc.idp.artefato.model.FollowRelationship;
import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.UserDTO;
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

    public Optional<FollowRelationship> createNewRelationship(UserDTO follower, UserDTO followed) {

        Optional<UserEntity> u1 = userRepository.getUser(follower.getName(), follower.getEmail());
        Optional<UserEntity> u2 = userRepository.getUser(followed.getName(), followed.getEmail());

        if (u1.isPresent() && u2.isPresent()) {

            return userRepository.createUserFollowRelationship(u1.get().getId(), u2.get().getId());

        }

        return Optional.empty();

    }

}
