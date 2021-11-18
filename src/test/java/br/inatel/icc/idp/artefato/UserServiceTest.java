package br.inatel.icc.idp.artefato;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.repository.UserRepository;
import br.inatel.icc.idp.artefato.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void testUserServiceIsReturningNotNull() {

        Collection<UserEntity> user = userService.getAllUsers();

        log.info(user.toString());

        assertNotNull(user);
    }

    @Test
    public void testUserServiceSaveUser() {

        UserEntity userEntity = new UserEntity("name", "email", "bio", false, new BigDecimal("0"));

        Optional<UserEntity> userSaved = userService.saveUserToDatabase(userEntity);

        userEntity = userEntity.withId(userSaved.get().getId());

        log.info(userEntity.toString());

        userRepository.removeUserAndAllAssets(userEntity.getId());

        assertEquals(userEntity, userSaved.get());

    }

    @Test
    public void testUserServiceGetUser() {

        UserEntity userEntity = new UserEntity("name", "email", "bio", false, new BigDecimal("0"));

        Optional<UserEntity> userSaved = userService.saveUserToDatabase(userEntity);

        userEntity = userEntity.withId(userSaved.get().getId());

        log.info(userEntity.toString());

        Optional<UserEntity> user = userRepository.getUserById(userEntity.getId());

        userRepository.removeUserAndAllAssets(userEntity.getId());

        assertEquals(userEntity, user.get());
    }
}
