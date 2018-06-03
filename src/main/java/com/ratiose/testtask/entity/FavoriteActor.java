package com.ratiose.testtask.entity;

import javax.persistence.*;

@Entity
public class FavoriteActor {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    private User user;

    Long actorId;

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

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }


}