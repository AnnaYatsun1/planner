package io.github.externschool.planner.entity;

import io.github.externschool.planner.dto.UserDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Transient
    @Column(name = "password")
    private String password;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Authority> authorities = new HashSet<>();

    public User() {
    }

    public User(String phoneNumber, String email, String password, String encryptedPassword) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
        authority.setUser(this);
    }

    public void removeAuthority(Authority authority) {
        authority.setUser(null);
        this.authorities.remove(authority);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(encryptedPassword, user.encryptedPassword) &&
                Objects.equals(authorities, user.authorities);
    }

    public UserDTO constructUser() {
        UserDTO useDtO = new UserDTO();
        useDtO.setEmail(this.getEmail());
        useDtO.setPassword(this.getPassword());
        useDtO.setPhoneNumber(this.getPhoneNumber());
        return useDtO;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, phoneNumber, email, password, encryptedPassword, authorities);
    }
}
