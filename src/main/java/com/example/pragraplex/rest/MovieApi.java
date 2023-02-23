package com.example.pragraplex.rest;

import com.example.pragraplex.entity.Movie;
import com.example.pragraplex.service.MovieService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieApi {
    private MovieService service;

    public MovieApi(MovieService service) {
        this.service = service;
    }

    @PostMapping("/movie")
    public Movie createMovie(@RequestBody Movie movie) {
        return service.createMovie(movie);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public Optional<Movie> getMovieById(@PathVariable(name = "id") Integer id) {
        return service.getMovieById(id);
    }

    @PostMapping("/movies")
    public List<Movie> addMultipleMovies(@RequestBody List<Movie> movies) {
        return service.addMultipleMovies(movies);
    }


    @PutMapping("/movie/update")
    public Movie updateMovieById( @RequestBody Movie movie){
        return service.updateMovie(movie);
    }


    @DeleteMapping("/movie/remove/{movieId}")
    public void deleteMovieById(@PathVariable int movieId){
        service.deleteMovieById(movieId);
    }


}
