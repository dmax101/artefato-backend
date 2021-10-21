package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.inatel.icc.idp.artefato.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = "Name can't be null")
    @Size(min = 3, message = "Name have to contain at least 3 characters")
    String name;
    @Email(message = "E-mail have to be valid")
    String email;
    @Size(max = 255, message = "Bio have to contain at maximum 255 characters")
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
