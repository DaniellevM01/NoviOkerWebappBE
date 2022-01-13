package nl.novi.okerwebapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String user_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int telephone_number;

    @Column(nullable = false)
    private String name;

    // Deze weghalen?
    //@Column(nullable = false)
    //private boolean enabled = true;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(
            targetEntity = OfferRequest.class,
            mappedBy = "user_id",
            fetch = FetchType.LAZY)
    private Set<OfferRequest> offerRequests = new HashSet<>();

    @OneToMany(
            targetEntity = VacancyRequest.class,
            mappedBy = "user_id",
            fetch = FetchType.LAZY)
    private Set<VacancyRequest> vacancyRequests = new HashSet<>();


    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //public boolean isEnabled() {
    //    return enabled;
    //}

    //public void setEnabled(boolean enabled) {
    //    this.enabled = enabled;
    //}

    public Set<Authority> getAuthorities() { return authorities; }

    public void addAuthority(String authorityString) {
        this.authorities.add(new Authority(this.username, authorityString));
    }

    public void removeAuthority(String authorityString) {
        this.authorities.removeIf(authority -> authority.getAuthority().equalsIgnoreCase(authorityString));
    }

    public int getTelephonenumber() {
        return telephone_number;
    }

    public void setTelephonenumber(int telephonenumber) {
        this.telephone_number = telephonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

