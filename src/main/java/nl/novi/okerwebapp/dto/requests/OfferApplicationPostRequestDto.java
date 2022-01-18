package nl.novi.okerwebapp.dto.requests;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OfferApplicationPostRequestDto {

    @NotBlank
    @Size(min=1, max=300, message="description is too long or too short")
    private String description;

    @NotBlank
    @Size(min=1, max=10, message="status is too long or too short")
    private String status;

    @NotBlank
    private MultipartFile file;


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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
