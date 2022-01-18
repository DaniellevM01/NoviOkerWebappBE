package nl.novi.okerwebapp.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VacancyGetRequestDto {

    @NotBlank
    @Size(min=1, max=30)
    private String title;

    @NotBlank
    @Size(min=1, max=1000)
    private int description;

    @NotBlank
    private boolean enabled;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
