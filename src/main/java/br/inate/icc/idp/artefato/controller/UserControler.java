package br.inate.icc.idp.artefato.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inate.icc.idp.artefato.model.User;
import br.inate.icc.idp.artefato.repository.UserRepository;
import br.inate.icc.idp.artefato.service.UserService;

@RestController
@RequestMapping("/user")
@Cacheable("UserCache")
public class UserControler {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
