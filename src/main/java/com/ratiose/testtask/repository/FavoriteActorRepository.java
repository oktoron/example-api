package com.ratiose.testtask.repository;

import com.ratiose.testtask.entity.FavoriteActor;
import com.ratiose.testtask.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteActorRepository extends CrudRepository<FavoriteActor, Long> {
    FavoriteActor findByUserAndActorId(User user, Long actorId);
}