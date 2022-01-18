package nl.novi.okerwebapp.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.File;
import java.sql.Timestamp;

@Entity
@Table(name = "VacancyApplications")

public class VacancyApplication {
    @Id
    @Column(nullable = false)
    private int vacancy_application_id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public int getId() {
        return vacancy_application_id;
    }

    public void setId(int id) {
        this.vacancy_application_id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
