package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;

import br.inatel.icc.idp.artefato.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String bio;
    private Boolean isCrafter;

    public UserEntity convetToEntity() {
        return new UserEntity(null, this.name, this.email, this.bio, this.isCrafter, new BigDecimal(0));
    }

    public static UserDTO convertToDTO(UserEntity response) {
        return new UserDTO(response.getId(), response.getName(), response.getEmail(), response.getBio(), response.getIsCrafter());
    }

}
