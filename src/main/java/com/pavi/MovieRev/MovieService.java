package com.pavi.MovieRev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public List<Movie> findAllMovies() {
        List<Movie> movies = repository.findAll();
        System.out.println("Fetched Movies: " + movies);
        return movies;
    }

    // the find by id might return null if movie does not exist
    // so we use optional to tell java that we might get null
    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return repository.findMovieByImdbId(imdbId);
    }
}
