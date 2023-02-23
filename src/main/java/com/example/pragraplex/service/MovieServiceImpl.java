package com.example.pragraplex.service;

import com.example.pragraplex.entity.Movie;
import com.example.pragraplex.exceptions.MovieNotFoundException;
import com.example.pragraplex.repo.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepo repo;

    public MovieServiceImpl(MovieRepo repo) {
        this.repo = repo;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return repo.save(movie);
    }
    @Override
    public List<Movie> getAllMovies() {
        return repo.findAll();
    }
    @Override
    public Optional<Movie> getMovieById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public List<Movie> addMultipleMovies(List<Movie> movies) {
        return repo.saveAll(movies);
    }

    @Override
    public Movie updateMovie(Movie movie) {
//        Movie movie1 = repo.findById(movieId).orElseThrow(
//                () -> new MovieNotFoundException(String.format("Movie with id %d not found", movieId))
//        );
//        movie1.setMovieName(movie.getMovieName());
//        movie1.setReleaseDate(movie.getReleaseDate());
//        movie1.setDurationInMinutes(movie.getDurationInMinutes());
        return repo.save(movie);
    }

    @Override
    public void deleteMovieById(int movieId) {
        repo.deleteById(movieId);
    }

}
