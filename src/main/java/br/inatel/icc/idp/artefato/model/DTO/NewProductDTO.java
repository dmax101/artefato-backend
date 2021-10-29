package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewProductDTO {

    private UUID idCrafter;
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve conter mais de 2 caracteres")
    private String name;
    @Size(max = 255, message = "A Bio deve ter no máximo 255 caracteres")
    private String description;
    @Positive(message = "Deve ser maior que zero")
    private BigDecimal price;
    @NotNull(message = "Não pode ser nulo")
    private Boolean isAvailable;
    private List<String> imageURL;
}
