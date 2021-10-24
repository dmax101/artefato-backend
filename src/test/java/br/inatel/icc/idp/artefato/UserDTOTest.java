package br.inatel.icc.idp.artefato;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDTOTest {

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void testUserDTOIsConvertingToUserEntity() {

        UserEntity userEntity = new UserEntity("name", "email", "bio", false, 0.0);

        UserDTO userDTO = UserDTO.convertToDTO(userEntity);

        log.info(userDTO.toString());

        assertNotNull(userDTO);

    }

}
