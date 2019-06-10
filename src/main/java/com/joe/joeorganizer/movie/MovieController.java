package com.joe.joeorganizer.movie;

import com.joe.joeorganizer.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private MovieService movieService;

    @GetMapping("/random")
    public Movie randomMovie(@RequestHeader("auth-x") String token) {
        String email = tokenUtil.getUserNameFromToken(token);
        return this.movieService.getRandomMovie(email);
    }

    @PostMapping("/")
    public void addMovie(@RequestHeader("auth-x") String token, @RequestBody @Valid Movie movie) {
        String email = tokenUtil.getUserNameFromToken(token);
        this.movieService.addMovie(email, movie);
    }

    @GetMapping("/")
    public List<Movie> allMovies(@RequestHeader("auth-x") String token) {
        String email = this.tokenUtil.getUserNameFromToken(token);
        return this.movieService.getAllMovies(email);
    }
}
