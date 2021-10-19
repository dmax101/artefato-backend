package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;
import java.util.UUID;

import br.inatel.icc.idp.artefato.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    String name;
    String email;
    String bio;
    Boolean isCrafter;
    BigDecimal wallet;

    public UserEntity convetToEntity() {
        UserEntity userEntity = new UserEntity(this.name, this.email, this.bio, this.isCrafter, this.wallet);
        return userEntity.withId(UUID.randomUUID());
    }

    public static UserDTO convertToDTO(UserEntity responseUserEntity) {
        return new UserDTO(responseUserEntity.getName(), responseUserEntity.getEmail(), responseUserEntity.getBio(),
                responseUserEntity.getIsCrafter(), responseUserEntity.getWallet());
    }

}
