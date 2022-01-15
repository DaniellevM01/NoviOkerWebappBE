package nl.novi.okerwebapp.model;

import javax.persistence.*;

@Entity
@Table(name = "OfferApplications")
public class OfferApplication {

    @Id
    @Column(nullable = false)
    private int offer_application_id;

    //moet van userklasse komen
    //@Id
    //@Column(nullable = false)
    //private String user_id;

    @Column(nullable = false)
    private String description;

    //fileupload
    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public int getId() {
        return offer_application_id;
    }

    public void setId(int id) {
        this.offer_application_id = id;
    }

    //public String getUserid() {
    //    return user_id;
    //}

    //public void setUserid(String userid) {
    //    this.user_id = user_id;
    //}

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
