package nl.novi.okerwebapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Authorities")
@IdClass(AuthorityKey.class)
public class Authority implements Serializable{

    @Id
    @Column(nullable = false)
    @JoinColumn(name = "username")
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

    // constructors

    public Authority() {}
    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    // getters and setters

    public String getUserName() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}