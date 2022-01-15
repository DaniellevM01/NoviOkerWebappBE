package nl.novi.okerwebapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Authorities")
@IdClass(AuthorityKey.class)
public class Authority implements Serializable{

    @Id
    @Column(nullable = false)
    private Integer user_id;

    @Id
    @Column(nullable = false)
    private String authority;

    // constructors

    public Authority() {}
    public Authority(Integer user_id, String authority) {
        this.user_id = user_id;
        this.authority = authority;
    }

    // getters and setters

    public Integer getUserId() {
        return user_id;
    }
    public void setUsername(Integer user_id) {
        this.user_id = user_id;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}