package com.ratiose.testtask.service.impl;

import com.ratiose.testtask.entity.FavoriteActor;
import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.repository.FavoriteActorRepository;
import com.ratiose.testtask.repository.UserRepository;
import com.ratiose.testtask.service.FavoriteActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class FavoriteActorImpl implements FavoriteActorService {

    @Autowired
    private FavoriteActorRepository favoriteActorRepository;

    @Override
    public FavoriteActor addFavoriteActor(User user, Long actorId) {
        if (favoriteActorRepository.findByUserAndActorId(user, actorId) != null) {
            return null;
        }
        FavoriteActor favoriteActor = createFavoriteActor(user, actorId);
        return favoriteActorRepository.save(favoriteActor);
    }

    @Override
    public void deleteFavoriteActor(User user, Long actorId) {
        FavoriteActor foundedFavoriteActor = favoriteActorRepository.findByUserAndActorId(user, actorId);
        if (nonNull(foundedFavoriteActor)) {
            favoriteActorRepository.delete(foundedFavoriteActor);
        }
    }

    private FavoriteActor createFavoriteActor(User user, Long actorId) {
        FavoriteActor favoriteActor = new FavoriteActor();
        favoriteActor.setActorId(actorId);
        favoriteActor.setUser(user);
        return favoriteActor;
    }

}