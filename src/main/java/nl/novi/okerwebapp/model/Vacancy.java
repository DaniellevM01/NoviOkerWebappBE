package nl.novi.okerwebapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Vacancies")

public class Vacancy {
    @Id
    @Column(nullable = false)
    private int vacancy_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(
            targetEntity = VacancyApplication.class,
            mappedBy = "vacancy_id",
            fetch = FetchType.LAZY)
    private Set<VacancyApplication> vacancyApplications = new HashSet<>();

    public Vacancy(int id, String title, String description, boolean enabled) {
        this.vacancy_id = id;
        this.title = title;
        this.description = description;
        this.enabled = enabled;
    }

    public int getId() {
        return vacancy_id;
    }

    public void setId(int id) {
        this.vacancy_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
