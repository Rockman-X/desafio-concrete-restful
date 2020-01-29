package com.example.desafioconcreterestful.dto;

import com.example.desafioconcreterestful.entities.Phone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String name;
    private String email;
    private LocalDate created;
    private LocalDateTime lastLogin;
    private LocalDateTime modified;
    private UUID token;
    private List<Phone> phones;


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

    public LocalDate getCreated() {

        return created;
    }

    public void setCreated(LocalDate created) {

        this.created = created;
    }

    public LocalDateTime getLastLogin() {

        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {

        this.lastLogin = lastLogin;
    }

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
}
