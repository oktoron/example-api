package com.ratiose.testtask.entity;

import javax.persistence.*;

@Entity
public class WatchedMovie {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    private User user;

    Long movieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

}