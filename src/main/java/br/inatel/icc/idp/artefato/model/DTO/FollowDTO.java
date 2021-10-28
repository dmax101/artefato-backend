package br.inatel.icc.idp.artefato.model.DTO;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowDTO {

    @NotNull
    private UUID followerId;
    @NotNull
    private UUID influencerId;

}
