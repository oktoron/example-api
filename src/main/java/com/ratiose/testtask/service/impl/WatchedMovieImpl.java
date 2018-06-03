package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.entity.WatchedMovie;
import com.ratiose.testtask.repository.UserRepository;
import com.ratiose.testtask.repository.WatchedMovieRepository;
import com.ratiose.testtask.service.WatchedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class WatchedMovieImpl implements WatchedMovieService {

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    @Override
    public WatchedMovie addWatchedMovie(User user, Long movieId) {
        if (watchedMovieRepository.findByUserAndMovieId(user, movieId) != null) {
            return null;
        }
        WatchedMovie watchedMovie = createWatchedMovie(user, movieId);
        return watchedMovieRepository.save(watchedMovie);
    }

    @Override
    public void deleteWatchedMovie(User user,  Long actorId) {
        WatchedMovie foundedWatchedMovie = watchedMovieRepository.findByUserAndMovieId(user, actorId);
        if (nonNull(foundedWatchedMovie)) {
            watchedMovieRepository.delete(foundedWatchedMovie);
        }
    }

    private WatchedMovie createWatchedMovie(User user, Long movieId) {
        WatchedMovie watchedMovie = new WatchedMovie();
        watchedMovie.setMovieId(movieId);
        watchedMovie.setUser(user);
        return watchedMovie;
    }

}