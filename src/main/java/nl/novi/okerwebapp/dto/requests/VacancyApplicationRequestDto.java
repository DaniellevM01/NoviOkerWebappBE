package nl.novi.okerwebapp.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VacancyApplicationRequestDto {

    @NotBlank
    @Size(min=1, max=30, message="status is too long or too short")
    private String status;

    @NotBlank
    @Size(min=1, max=300, message="remarks is too long or too short")
    private String description;

    @NotBlank
    private int vacancy_id;

    //file!
    //@NotBlank
    //@Size(min=1, max=300, message="remarks is too long or too short")
    //private file;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVacancy_id() {
        return vacancy_id;
    }

    public void setVacancy_id(int vacancy_id) {
        this.vacancy_id = vacancy_id;
    }
}
