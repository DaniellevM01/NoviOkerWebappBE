package nl.novi.okerwebapp.model;

import javax.persistence.*;

@Entity
@Table(name = "VacancyRequests")

public class VacancyRequest {
    @Id
    @Column(nullable = false)
    private int vacancy_id;

    //@Id
    //@Column(nullable = false)
    //private int user_id;

    @Column(nullable = false)
    private String description;

    //file
    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private int user_id;

    public int getId() {
        return vacancy_id;
    }

    public void setId(int id) {
        this.vacancy_id = id;
    }

    public int getUserid() {
        return user_id;
    }

    public void setUserid(int userid) {
        this.user_id = userid;
    }

    public int getVacancy() {
        return vacancy_id;
    }

    public void setVacancy(int vacancy) {
        this.vacancy_id = vacancy;
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
