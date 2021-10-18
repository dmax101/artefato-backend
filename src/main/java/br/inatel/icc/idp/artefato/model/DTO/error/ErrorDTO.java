package br.inatel.icc.idp.artefato.model.DTO.error;

import java.util.List;
import java.util.Map;

public class ErrorDTO extends BasicMessageDTO {

    private List<Map<String, String>> error;

    public ErrorDTO() {
    }

    public ErrorDTO(List<Map<String,String>> error) {
        this.error = error;
    }

    public ErrorDTO(String status, String description, List<Map<String,String>> error) {
        this.status = status;
        this.description = description;
        this.error = error;
    }

    public List<Map<String,String>> getError() {
        return this.error;
    }

    public void setError(List<Map<String,String>> error) {
        this.error = error;
    }

}
