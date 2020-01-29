package com.example.desafioconcreterestful.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @NotBlank(message = "{name.not.blank}")
    private String name;

    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    private String email;

    @NotBlank(message = "{senha.not.blank}")
    private String password;

    private LocalDate created;
    private LocalDateTime lastLogin;
    private LocalDateTime modified;

    @Column(columnDefinition = "VARCHAR(255)")
    private UUID token;

    @OneToMany(mappedBy = "user")
    private List<Phone> phones;

    public User() {

        super();
    }

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

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

    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate getCreated() {

        return created;
    }

    public void setCreated(LocalDate created) {

        this.created = created;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime getLastLogin() {

        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {

        this.lastLogin = lastLogin;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime getModified() {

        return modified;
    }

    public void setModified(LocalDateTime modified) {

        this.modified = modified;
    }

    public UUID getToken() {

        return token;
    }

    public void setToken(UUID token) {

        this.token = token;
    }

    public List<Phone> getPhones() {

        return phones;
    }

    public void setPhones(List<Phone> phones) {

        this.phones = phones;
    }

    public void generateToken() {

        this.token = UUID.randomUUID();
    }

}