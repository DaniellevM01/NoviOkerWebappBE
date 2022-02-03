package nl.novi.okerwebapp.dto.responses;

public class UserCreateResponseDto {

    private final String jwt;
    private final String username;

    public UserCreateResponseDto(String jwt, String username) {
        this.jwt = jwt;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }
}
