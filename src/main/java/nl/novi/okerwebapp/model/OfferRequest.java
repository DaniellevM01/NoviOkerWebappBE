package nl.novi.okerwebapp.model;

import javax.persistence.*;

@Entity
@Table(name = "OfferRequests")

public class OfferRequest {

    @Id
    @Column(nullable = false)
    private int id;

    //moet van userklasse komen
    @Id
    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String description;

    //fileupload
    @Id
    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    public OfferRequest(int id, String userid, String description, String file, String status) {
        this.id = id;
        this.userid = userid;
        this.description = description;
        this.file = file;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
