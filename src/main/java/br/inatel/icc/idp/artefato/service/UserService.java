package br.inatel.icc.idp.artefato.service;

import java.util.Collection;

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

    public UserDTO saveToDatabase(UserDTO userDTO) {

        UserEntity userEntity = userDTO.convetToEntity();

        try {
            log.info("Saving user in database");
            UserEntity response = userRepository.save(userEntity);

            return UserDTO.convertToDTO(response);
        } catch (Exception e) {
            log.error("It was not possible to save in database", e);
            
            return null;
        }
    }

}
