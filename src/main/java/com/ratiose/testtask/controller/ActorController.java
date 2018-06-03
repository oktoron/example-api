package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.service.FavoriteActorService;
import com.ratiose.testtask.service.UserService;
import com.ratiose.testtask.service.WatchedMovieService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteActorService favoriteActorService;

    @Autowired
    private TmdbApi tmdbApi;

    @RequestMapping(value = "/follow", method = POST)
    public ResponseEntity addWatchedMovie(@RequestParam String email,
                                          @RequestParam String password,
                                          @RequestParam Long actorId,
                                          HttpSession session) {
        User user = userService.findUser(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (favoriteActorService.addFavoriteActor(user, actorId) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/unfollow", method = DELETE)
    public ResponseEntity removeWatchedMovie(@RequestParam String email,
                                             @RequestParam String password,
                                             @RequestParam Long actorId,
                                             HttpSession session) {
        User user = userService.findUser(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        favoriteActorService.deleteFavoriteActor(user, actorId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
