package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.FavoriteActor;
import com.ratiose.testtask.entity.User;

public interface FavoriteActorService {
    FavoriteActor addFavoriteActor(User user, Long actorId);
    void deleteFavoriteActor(User user, Long actorId);
}

