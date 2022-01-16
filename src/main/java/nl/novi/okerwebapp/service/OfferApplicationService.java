package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.repository.UserRepository;
import nl.novi.okerwebapp.dto.requests.OfferApplicationRequestDto;
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

    @Value("${app.upload_location}")
    private String uploadPath;

    public Iterable<OfferApplication> getOfferApplications() {
        return offerApplicationRepository.findAll();
    }

    public int addOfferApplication(OfferApplicationRequestDto offerApplicationRequestDto) {

        OfferApplication offerApplication = new OfferApplication();
        offerApplication.setStatus(offerApplicationRequestDto.getStatus());
        offerApplication.setDescription(offerApplicationRequestDto.getDescription());
        offerApplication.setUser(userRepository.findById(1).orElseThrow());
        if(!offerApplicationRequestDto.getFile().isEmpty()){
            try{
                Path root = Paths.get(uploadPath);
                if (!Files.exists(root)) {
                    Files.createDirectories(Paths.get(uploadPath));
                }
                Path copyLocation = root.resolve(offerApplicationRequestDto.getFile().getOriginalFilename());
                Files.copy(offerApplicationRequestDto.getFile().getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                offerApplication.setFile(offerApplicationRequestDto.getFile().getOriginalFilename());
            }catch (IOException e){
                throw new RuntimeException("Could not create upload folder!");
            }catch (Exception e){
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
        OfferApplication newOfferApplication = offerApplicationRepository.save(offerApplication);
        return newOfferApplication.getId();
    }

    public void partialUpdateOfferApplication(int id, OfferApplication offerApplication) {
        Optional<OfferApplication> optionalOfferApplication = offerApplicationRepository.findById(id);

        if (optionalOfferApplication.isPresent()) {
            OfferApplication storedOfferApplication = offerApplicationRepository.findById(id).orElse(null);

            if (offerApplication.getStatus() != null && !offerApplication.getStatus().isEmpty()) {
                storedOfferApplication.setStatus(offerApplication.getStatus());
            }
            offerApplicationRepository.save(storedOfferApplication);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
