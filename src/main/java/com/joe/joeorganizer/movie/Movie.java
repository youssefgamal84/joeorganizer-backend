package com.joe.joeorganizer.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joe.joeorganizer.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    @NotNull
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_email")
    private User user;

    public Movie() {
    }

    public Movie(int id, @NotEmpty @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
