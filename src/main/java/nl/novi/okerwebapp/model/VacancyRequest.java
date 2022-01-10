package nl.novi.okerwebapp.model;

import javax.persistence.*;

@Entity
@Table(name = "VacancyRequests")

public class VacancyRequest {
    @Id
    @Column(nullable = false)
    private int id;

    //moet uit user komen
    @Id
    @Column(nullable = false)
    private int userid;

    @Column(nullable = false)
    private String remarks;

    //file
    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getVacancy() {
        return vacancy_id;
    }

    public void setVacancy(String vacancy) {
        this.vacancy_id = vacancy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
