package br.inatel.icc.idp.artefato.model.DTO;

public class BasicMessageDTO {

    protected String status;
    protected String description;

    public BasicMessageDTO() {
    }

    public BasicMessageDTO(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
