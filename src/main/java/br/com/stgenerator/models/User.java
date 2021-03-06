package br.com.stgenerator.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user_all")
public class User {

    @Id
    @SequenceGenerator(name = "USER_ALL_ID", sequenceName = "USER_ALL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ALL_ID")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String favoriteMeme;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_all_role", joinColumns = @JoinColumn(name = "user_all_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavoriteMeme() {
        return favoriteMeme;
    }

    public void setFavoriteMeme(String favoriteMeme) {
        this.favoriteMeme = favoriteMeme;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
