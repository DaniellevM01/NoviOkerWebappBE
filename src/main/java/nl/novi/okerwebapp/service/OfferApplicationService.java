package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.OfferApplicationPatchRequestDto;
import nl.novi.okerwebapp.exception.BadRequestException;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.repository.UserRepository;
import nl.novi.okerwebapp.dto.requests.OfferApplicationPostRequestDto;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.OfferApplication;
import nl.novi.okerwebapp.repository.OfferApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


@Service
public class OfferApplicationService {

    @Autowired
    private OfferApplicationRepository offerApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Value("${app.upload_location}")
    private String uploadPath;

    public Iterable<OfferApplication> getOfferApplications() {
        return offerApplicationRepository.findAll();
    }

    public int addOfferApplication(OfferApplicationPostRequestDto offerApplicationPostRequestDto) {
        User user = (User)this.userService.getCurrentUser().orElseThrow();
        boolean is_customer = this.userService.verifyAuthority(user.getUserId(), "CUSTOMER");

        if(!is_customer){
            throw new BadRequestException("User is not a customer!");
        }

        OfferApplication offerApplication = new OfferApplication();
        offerApplication.setStatus(offerApplicationPostRequestDto.getStatus());
        offerApplication.setDescription(offerApplicationPostRequestDto.getDescription());
        offerApplication.setUser(userRepository.findById(1).orElseThrow());
        if(!offerApplicationPostRequestDto.getFile().isEmpty()){
            try{
                Path root = Paths.get(uploadPath);
                if (!Files.exists(root)) {
                    Files.createDirectories(Paths.get(uploadPath));
                }
                Path copyLocation = root.resolve(offerApplicationPostRequestDto.getFile().getOriginalFilename());
                Files.copy(offerApplicationPostRequestDto.getFile().getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                offerApplication.setFile(offerApplicationPostRequestDto.getFile().getOriginalFilename());
            }catch (IOException e){
                throw new RuntimeException("Could not create upload folder!");
            }catch (Exception e){
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
        OfferApplication newOfferApplication = offerApplicationRepository.save(offerApplication);
        return newOfferApplication.getId();
    }

    public void partialUpdateOfferApplication(int id, OfferApplicationPatchRequestDto offerApplicationPatchRequestDto) {
        Optional<OfferApplication> optionalOfferApplication = offerApplicationRepository.findById(id);

        if (optionalOfferApplication.isPresent()) {
            OfferApplication storedOfferApplication = offerApplicationRepository.findById(id).orElse(null);

            if (offerApplicationPatchRequestDto.getStatus() != null && !offerApplicationPatchRequestDto.getStatus().isEmpty()) {
                storedOfferApplication.setStatus(offerApplicationPatchRequestDto.getStatus());
            }
            offerApplicationRepository.save(storedOfferApplication);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
