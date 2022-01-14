package nl.novi.okerwebapp.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OfferApplicationRequestDto {

    @NotBlank
    @Size(min=1, max=300, message="description is too long or too short")
    private String description;

    @NotBlank
    @Size(min=1, max=10, message="status is too long or too short")
    private String status;

    //file!
    //@NotBlank
    //@Size(min=1, max=10)
    //private  file;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
