package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.dto.requests.AuthenticationRequestDto;
import nl.novi.okerwebapp.dto.responses.AuthenticationResponseDto;
import nl.novi.okerwebapp.service.UserAuthenticateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    UserAuthenticateService userAuthenticateService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        AuthenticationResponseDto authenticationResponseDto = userAuthenticateService.authenticateUser(authenticationRequestDto);

        return ResponseEntity.ok(authenticationResponseDto);
    }

}
