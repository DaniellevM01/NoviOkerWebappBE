package nl.novi.okerwebapp.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

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

    public int getOffer_application_id() {
        return offer_application_id;
    }

    public void setOffer_application_id(int offer_application_id) {
        this.offer_application_id = offer_application_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
