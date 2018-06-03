package com.ratiose.testtask.repository;

import com.ratiose.testtask.entity.FavoriteActor;
import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.entity.WatchedMovie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WatchedMovieRepository extends CrudRepository<WatchedMovie, Long> {
    List<WatchedMovie> findByUserId(Long userId);
    WatchedMovie findByUserAndMovieId(User user, Long movieId);
}