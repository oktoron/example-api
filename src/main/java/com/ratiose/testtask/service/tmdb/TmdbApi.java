package com.ratiose.testtask.service.tmdb;

import java.util.Date;
import java.util.List;

public interface TmdbApi {
    String popularMovies();
    String searchByDateAndActors(String dateFrom, String dateTo, List<String> actors);
}
