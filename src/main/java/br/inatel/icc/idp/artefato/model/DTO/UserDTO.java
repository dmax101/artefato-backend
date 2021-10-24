package br.inatel.icc.idp.artefato.model.DTO;

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

    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve conter mais de 2 caracteres")
    String name;
    @Email(message = "O e-mail dever ser bem formatado")
    String email;
    @Size(max = 255, message = "A Bio deve ter no máximo 255 caracteres")
    String bio;
    Boolean isCrafter;
    Double wallet;

    public UserEntity convetToEntity() {
        UserEntity userEntity = new UserEntity(this.name, this.email, this.bio, this.isCrafter, this.wallet);
        return userEntity.withId(UUID.randomUUID());
    }

    public static UserDTO convertToDTO(UserEntity responseUserEntity) {
        return new UserDTO(responseUserEntity.getName(), responseUserEntity.getEmail(), responseUserEntity.getBio(),
                responseUserEntity.getIsCrafter(), responseUserEntity.getWallet());
    }

}
