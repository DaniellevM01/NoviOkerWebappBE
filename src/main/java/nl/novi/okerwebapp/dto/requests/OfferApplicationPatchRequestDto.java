package nl.novi.okerwebapp.dto.requests;

import javax.validation.constraints.NotBlank;

public class OfferApplicationPatchRequestDto {

    @NotBlank
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
