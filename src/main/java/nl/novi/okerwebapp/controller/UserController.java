package nl.novi.okerwebapp.controller;


import nl.novi.okerwebapp.dto.requests.UserPostRequestDto;
import nl.novi.okerwebapp.exception.BadRequestException;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(value = "/{user_id}")
    public ResponseEntity<Object> getUser(@PathVariable("user_id") Integer user_id) {
        return ResponseEntity.ok().body(userService.getUser(user_id));
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody UserPostRequestDto user) {
        String newUsername = userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUsername)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{user_id}")
    public ResponseEntity<Object> updateUser(@PathVariable("user_id") Integer user_id, @RequestBody User user) {
        userService.updateUser(user_id, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{user_id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user_id") Integer user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{user_id}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("user_id") Integer user_id) {
        return ResponseEntity.ok().body(userService.getAuthorities(user_id));
    }

    @PostMapping(value = "/{user_id}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("user_id") Integer user_id, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(user_id, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{user_id}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("user_id") Integer user_id, @PathVariable("authority") String authority) {
        userService.removeAuthority(user_id, authority);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{user_id}/password")
    public ResponseEntity<Object> setPassword(@PathVariable("user_id") Integer user_id, @RequestBody String password) {
        userService.setPassword(user_id, password);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/resetpassword")
    public ResponseEntity<Object> resetPassword(@RequestBody String email) {
        userService.resetUserPassword(email);
        return ResponseEntity.noContent().build();
    }


}
