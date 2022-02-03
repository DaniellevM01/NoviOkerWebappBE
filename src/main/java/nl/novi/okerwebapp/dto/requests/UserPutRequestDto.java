package nl.novi.okerwebapp.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserPutRequestDto {

    @Size(min=10, max=10)
    private String telephone_number;

    @NotBlank
    private String name;

    public UserPutRequestDto(String name, String telephone_number) {
        this.name = name;
        this.telephone_number = telephone_number;
    }

    public String getTelephoneNumber() {
        return telephone_number;
    }

    public void setTelephoneNumber(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}
