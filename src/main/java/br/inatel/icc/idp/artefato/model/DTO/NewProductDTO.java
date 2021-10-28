package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewProductDTO {

    private final UUID idCrafter;
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve conter mais de 2 caracteres")
    private final String name;
    @Size(max = 255, message = "A Bio deve ter no máximo 255 caracteres")
    private final String description;
    @Positive(message = "Deve ser maior que zero")
    private final BigDecimal price;
    @NotNull(message = "Não pode ser nulo")
    private final Boolean isAvailable;
    private final List<String> imageURL;
}
