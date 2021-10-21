package br.inatel.icc.idp.artefato.model.DTO;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    @NotNull(message = "Name can't be null")
    @Size(min = 3, message = "Name have to contain at least 3 characters")
    private final String name;
    @NotNull(message = "Description can't be null")
    @Size(max = 255, message = "Description have to contain at maximum 255 characters")
    private final String description;
    private final BigDecimal price;
    private final Boolean isAvailable;
    private final List<String> imageURL;

    public static ProductDTO convertToDTO(ProductEntity productEntity) {
        return new ProductDTO(productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(),
                productEntity.getIsAvailable(), productEntity.getImageURL());
    }

}
