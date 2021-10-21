package br.inatel.icc.idp.artefato.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import br.inatel.icc.idp.artefato.model.DTO.ProductDTO;

@Service
public class ProductService {

    public Collection<ProductDTO> convertListEntityToListDTO(Collection<ProductEntity> responseProductEntities) {

        return responseProductEntities.stream().map(ProductDTO::convertToDTO).collect(Collectors.toList());

    }

}
