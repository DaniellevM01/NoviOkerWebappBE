package nl.novi.okerwebapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private Integer user_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telephone_number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled = true;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "user_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(
            targetEntity = OfferApplication.class,
            mappedBy = "user_id",
            fetch = FetchType.LAZY)
    private Set<OfferApplication> offerApplications = new HashSet<>();

    @OneToMany(
            targetEntity = VacancyApplication.class,
            mappedBy = "user_id",
            fetch = FetchType.LAZY)
    private Set<VacancyApplication> vacancyApplications = new HashSet<>();

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() { return authorities; }

    public void addAuthority(String authorityString) {
        this.authorities.add(new Authority(this.user_id, authorityString));
    }

    public void removeAuthority(String authorityString) {
        this.authorities.removeIf(authority -> authority.getAuthority().equalsIgnoreCase(authorityString));
    }

    public String getTelephonenumber() {
        return telephone_number;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephone_number = telephonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

