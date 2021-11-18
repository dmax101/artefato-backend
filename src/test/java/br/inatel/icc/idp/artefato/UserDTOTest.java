package br.inatel.icc.idp.artefato;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class UserDTOTest {

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void testUserDTOIsConvertingToUserDTO() {

        UserEntity userEntity = new UserEntity("name", "email", "bio", false, new BigDecimal("0"));

        UserDTO userDTO = UserDTO.convertToDTO(userEntity);

        log.info(userDTO.toString());

        assertNotNull(userDTO);

    }

    @Test
    public void testUserDTOIsConvertingToUserEntity() {

        UserDTO userDTO = new UserDTO("name", "email", "bio", false, new BigDecimal("0"));

        UserEntity userEntity = userDTO.convetToEntity();

        log.info(userEntity.toString());

        assertNotNull(userEntity);

    }

    @Test
    public void testUserIDEntityType() {

        UserEntity userEntity = new UserEntity("name", "email", "bio", false, new BigDecimal("0"));

        UUID id = userEntity.withId(UUID.randomUUID()).getId();

        log.info(id.toString());

        assertSame(UUID.class, id.getClass());
    }

}
