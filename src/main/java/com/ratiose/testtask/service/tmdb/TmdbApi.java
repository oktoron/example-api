package com.ratiose.testtask.service.tmdb;

import java.util.Date;
import java.util.List;

public interface TmdbApi {
    String popularMovies();
    String searchByDateAndActors(Date dateFrom, Date dateTo, List<String> actors);
}
