package nl.novi.okerwebapp.dto.requests;

import javax.validation.constraints.Size;

public class UserPutRequestDto {

    @Size(min=10, max=10)
    private String telephone_number;

    @Size(min=8)
    private String password;

    public UserPutRequestDto() {
        this.password = password;
        this.telephone_number = telephone_number;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
