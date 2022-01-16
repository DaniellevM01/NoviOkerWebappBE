package nl.novi.okerwebapp.model;

import javax.persistence.*;

@Entity
@Table(name = "OfferApplications")
public class OfferApplication {

    @Id
    @Column(nullable = false)
    private int offer_application_id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public int getId() {
        return offer_application_id;
    }

    public void setId(int id) {
        this.offer_application_id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
