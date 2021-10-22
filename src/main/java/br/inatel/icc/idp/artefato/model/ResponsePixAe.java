package br.inatel.icc.idp.artefato.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePixAe {

    private String status;
    private String qrbase64;
    private String qrstring;
    private String idfatura;
    private String urlpixae;

}
