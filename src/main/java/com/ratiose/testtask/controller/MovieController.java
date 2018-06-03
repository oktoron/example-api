package com.ratiose.testtask.controller;

import com.ratiose.testtask.entity.FavoriteActor;
import com.ratiose.testtask.entity.User;
import com.ratiose.testtask.entity.WatchedMovie;
import com.ratiose.testtask.service.UserService;
import com.ratiose.testtask.service.WatchedMovieService;
import com.ratiose.testtask.service.tmdb.TmdbApi;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private UserService userService;

    @Autowired
    private WatchedMovieService watchedMovieService;

    @Autowired
    private TmdbApi tmdbApi;

    @RequestMapping(value = "/popular", method = POST)
    public ResponseEntity popular(@RequestParam String email,
                                  @RequestParam String password,
                                  HttpSession session) {
        if (userService.findUser(email, password) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String popularMovies = tmdbApi.popularMovies();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(popularMovies);
    }

    @RequestMapping(value = "/watch", method = POST)
    public ResponseEntity addWatchedMovie(@RequestParam String email,
                                          @RequestParam String password,
                                          @RequestParam Long movieId,
                                          HttpSession session) {
        User user = userService.findUser(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (watchedMovieService.addWatchedMovie(user, movieId) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/unwatch", method = DELETE)
    public ResponseEntity removeWatchedMovie(@RequestParam String email,
                                          @RequestParam String password,
                                          @RequestParam Long movieId,
                                          HttpSession session) {
        User user = userService.findUser(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        watchedMovieService.deleteWatchedMovie(user, movieId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/search", method = POST)
    public ResponseEntity getMoviesWithFavoriteActorsByDate(@RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String dateFrom,
                                  @RequestParam String dateTo,
                                  HttpSession session) {
        User user = userService.findUser(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<String> actors =
                user.getFavoriteActors().stream()
                        .map(FavoriteActor::getActorId)
                        .map(String::valueOf)
                        .collect(Collectors.toList());
        List<String> watchedMovies =
                user.getWatchedMovies().stream()
                        .map(WatchedMovie::getMovieId)
                        .map(String::valueOf)
                        .collect(Collectors.toList());
        String movies = tmdbApi.searchByDateAndActors(dateFrom, dateTo, actors);
        JSONObject jsonObject = new JSONObject(movies);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONArray list = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj= jsonArray.getJSONObject(i);
            if(watchedMovies.contains(obj.getString("id")))
            {
                list.put(obj);
            }
        }
        jsonObject.remove("results");
        jsonObject.accumulate("results", list);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(movies);
    }

}
