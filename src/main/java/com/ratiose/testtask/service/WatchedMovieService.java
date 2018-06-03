package com.ratiose.testtask.service;

import com.ratiose.testtask.entity.WatchedMovie;
import com.ratiose.testtask.entity.User;

public interface WatchedMovieService {
    WatchedMovie addWatchedMovie(User user, Long actorId);
    void deleteWatchedMovie(User user, Long actorId);
}

