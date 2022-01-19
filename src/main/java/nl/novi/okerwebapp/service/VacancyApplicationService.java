package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.exception.BadRequestException;
import nl.novi.okerwebapp.model.User;
import org.springframework.beans.factory.annotation.Value;
import nl.novi.okerwebapp.dto.requests.VacancyApplicationPostRequestDto;
import nl.novi.okerwebapp.exception.RecordNotFoundException;
import nl.novi.okerwebapp.model.Vacancy;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.repository.VacancyApplicationRepository;
import nl.novi.okerwebapp.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class VacancyApplicationService {

    @Autowired
    private VacancyApplicationRepository vacancyApplicationRepository;
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private UserService userService;


    @Value("${app.upload_location}")
    private String uploadPath;

    public Iterable<VacancyApplication> getVacancyApplications() {
        return vacancyApplicationRepository.findAll();
    }

    public int addVacancyApplication(VacancyApplicationPostRequestDto vacancyApplicationPostRequestDto) {
        User user = userService.getCurrentUser().orElseThrow();
        boolean is_sollicitor = user.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equalsIgnoreCase("SOLLICITOR"));

        if(!is_sollicitor){
            throw new BadRequestException("User is not a sollicitor!");
        }

        VacancyApplication vacancyApplication = new VacancyApplication();
        vacancyApplication.setStatus(vacancyApplicationPostRequestDto.getStatus());
        vacancyApplication.setDescription(vacancyApplicationPostRequestDto.getDescription());
        vacancyApplication.setUser(user);
        if(!vacancyApplicationPostRequestDto.getFile().isEmpty()){
            try{
                Path root = Paths.get(uploadPath);
                if (!Files.exists(root)) {
                    Files.createDirectories(Paths.get(uploadPath));
                }
                Path copyLocation = root.resolve(vacancyApplicationPostRequestDto.getFile().getOriginalFilename());
                Files.copy(vacancyApplicationPostRequestDto.getFile().getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                vacancyApplication.setFile(vacancyApplicationPostRequestDto.getFile().getOriginalFilename());
            }catch (IOException e){
                throw new RuntimeException("Could not create upload folder!");
            }catch (Exception e){
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
        Vacancy vacancy = vacancyRepository.findById(vacancyApplicationPostRequestDto.getVacancy_id()).orElseThrow();
        vacancyApplication.setVacancy(vacancy);

        VacancyApplication newVacancyApplication = vacancyApplicationRepository.save(vacancyApplication);
        return newVacancyApplication.getId();
    }

    public void partialUpdateVacancyApplication(int id, VacancyApplication vacancyApplication) {
        Optional<VacancyApplication> optionalOfferRequest = vacancyApplicationRepository.findById(id);

        if (optionalOfferRequest.isPresent()) {
            VacancyApplication storedVacancyApplication = vacancyApplicationRepository.findById(id).orElse(null);

            if (vacancyApplication.getStatus() != null && !vacancyApplication.getStatus().isEmpty()) {
                storedVacancyApplication.setStatus(vacancyApplication.getStatus());
            }
            vacancyApplicationRepository.save(storedVacancyApplication);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
