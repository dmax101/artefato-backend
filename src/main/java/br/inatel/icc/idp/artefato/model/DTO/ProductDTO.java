package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve conter mais de 2 caracteres")
    private final UUID id;
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve conter mais de 2 caracteres")
    private final String name;
    @NotNull(message = "Descrição não pode ser nula")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private final String description;
    @NotNull(message = "Preço não pode ser nulo")
    private final BigDecimal price;
    @NotNull(message = "Disponibilidade não pode ser nula")
    private final Boolean isAvailable;
    @NotNull(message = "Imagem não pode ser nula")
    private final List<String> imageURL;

    public static ProductDTO convertToDTO(ProductEntity productEntity) {
        return new ProductDTO(productEntity.getId(), productEntity.getName(), productEntity.getDescription(),
                productEntity.getPrice(), productEntity.getIsAvailable(), productEntity.getImageURL());
    }

}
