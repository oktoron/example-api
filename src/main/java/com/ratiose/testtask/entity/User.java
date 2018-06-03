package com.ratiose.testtask.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column(unique=true)
    String email;

    @OneToMany(mappedBy="user")
    private Collection<FavoriteActor> favoriteActors;

    @OneToMany(mappedBy="user")
    private Collection<WatchedMovie> watchedMovies;

    String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<FavoriteActor> getFavoriteActors() {
        return favoriteActors;
    }

    public Collection<WatchedMovie> getWatchedMovies() {
        return watchedMovies;
    }
}