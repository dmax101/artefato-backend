package br.inate.icc.idp.artefato.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inate.icc.idp.artefato.model.UserEntity;
import br.inate.icc.idp.artefato.repository.UserRepository;
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

}
