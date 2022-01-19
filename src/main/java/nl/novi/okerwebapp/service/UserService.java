package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.UserPostRequestDto;
import nl.novi.okerwebapp.exception.BadRequestException;
import nl.novi.okerwebapp.exception.InvalidPasswordException;
import nl.novi.okerwebapp.exception.NotAuthorizedException;
import nl.novi.okerwebapp.exception.UserNotFoundException;
import nl.novi.okerwebapp.model.Authority;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public Optional<User> getCurrentUser(){
        // TODO; wat als er geen ingelogde gebruiker is?
        return userRepository.findByUsername(getCurrentUserName());
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(int user_id) {
        return userRepository.findById(user_id);
    }

    public String createUser(UserPostRequestDto userPostRequest) {
        if(!isValidUsername(userPostRequest.getUsername()) || !isValidPassword(userPostRequest.getPassword())) {
            throw new BadRequestException("Username or password does not match required standards");
        }

        try {
            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());

            User user = new User();
            user.setUsername(userPostRequest.getUsername());
            user.setPassword(encryptedPassword);
            user.addAuthority("ROLE_USER");
            for (String s : userPostRequest.getAuthorities()) {
                if (!s.startsWith("ROLE_")) {
                    s = "ROLE_" + s;
                }
                s = s.toUpperCase();
                if (!s.equals("ROLE_USER")) {
                    user.addAuthority(s);
                }
            }
            User newUser = userRepository.save(user);
            createUserEmail(userPostRequest);
            return newUser.getUsername();
        }
        catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }

    }

    private void createUserEmail(UserPostRequestDto userPostRequestDto) {
        String body = "Welkom! \n" +
                "Bedankt voor het aanmaken van een account en het indienen van uw verzoek. \n" +
                "\n" +
                "U kunt nu inloggen met het door u gekozen email-adres en wachtwoord. \n" +
                "Via uw account kunt u de behandeling van uw aanvraag volgen";

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("Welkom!");
        mail.setText(body);
        mail.setTo(userPostRequestDto.getUsername());
        //mail.send
    }

    public void deleteUser(Integer user_id) {
        if (userRepository.existsById(user_id)) {
            userRepository.deleteById(user_id);
        }
        else {
            throw new UserNotFoundException(user_id.toString());
        }
    }

    public void updateUser(Integer user_id, User newUser) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(user_id.toString());
        }
        else {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(user);

        }
    }

    public void resetUserPassword(String email){
        Optional<User> userOptional = userRepository.findByUsername(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(email);
        }
        else {
            String newPassword = generateRandomPassword();
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            sendResetUserPasswordMail(user.getUsername(), newPassword);
        }
    }

    private String generateRandomPassword(){
        char[] lowerAlphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] specialCharacters = "@#$%&*!()+=-_".toCharArray();

        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i <= 5; i++){
            password.append(lowerAlphabet[random.nextInt(lowerAlphabet.length)]);
            password.append(upperAlphabet[random.nextInt(upperAlphabet.length)]);
            password.append(specialCharacters[random.nextInt(specialCharacters.length)]);
            password.append(random.nextInt(9));
        }

        return password.toString();
    }

    //MAIL STUREN
    public void sendResetUserPasswordMail(String email, String newPassword) {
        String body = "Goedendag, \n" +
                "Uw wachtwoord is gewijzigd. Uw tijdelijke wachtwoord is:" + newPassword + "\n";

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("Wachtwoord gewijzigd");
        mail.setText(body);
        mail.setTo(email);
        //mail.send
    }

    public Set<Authority> getAuthorities(Integer user_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(user_id.toString());
        }
        else {
            User user = userOptional.get();
            return user.getAuthorities();
        }
    }

    public void addAuthority(Integer user_id, String authorityString) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(user_id.toString());
        }
        else {
            User user = userOptional.get();
            user.addAuthority(authorityString);
            userRepository.save(user);
        }
    }

    public void removeAuthority(Integer user_id, String authorityString) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(user_id.toString());
        }
        else {
            User user = userOptional.get();
            user.removeAuthority(authorityString);
            userRepository.save(user);
        }
    }

    private boolean isValidUsername(String username) {
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@";

        long countSpecial = username.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validUsername = true;
        if (countSpecial < MIN_SPECIAL) validUsername = false;

        return validUsername;
    }

    private boolean isValidPassword(String password) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }

    public void setPassword(Integer user_id, String password) {
        //TODO correct user_id
        User receivedUser = userRepository.findById(user_id).orElseThrow();
        if (receivedUser.getUsername().equals(getCurrentUserName())) {
            if (isValidPassword(password)) {
                Optional<User> userOptional = userRepository.findById(user_id);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                }
                else {
                    throw new UserNotFoundException(user_id.toString());
                }
            }
            else {
                throw new InvalidPasswordException();
            }
        }

        else {
           throw new NotAuthorizedException();
        }
    }

}