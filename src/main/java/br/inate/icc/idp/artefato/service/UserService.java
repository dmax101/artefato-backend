package br.inate.icc.idp.artefato.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inate.icc.idp.artefato.model.User;
import br.inate.icc.idp.artefato.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
