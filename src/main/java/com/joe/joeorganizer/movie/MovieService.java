package com.joe.joeorganizer.movie;

import com.joe.joeorganizer.exceptions.UnauthorizedActionException;
import com.joe.joeorganizer.users.User;
import com.joe.joeorganizer.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private UserRepo userRepo;


    public List<Movie> getAllMovies(String email){
        User user = userRepo.getOne(email);
        return user.getMovies();
    }

    public Movie getRandomMovie(String email){
        List<Movie> allMovies = getAllMovies(email);
        int i = new Random().nextInt(allMovies.size());
        return allMovies.get(i);
    }

    public void addMovie(String email, Movie movie){
        User user = userRepo.getOne(email);
        movie.setUser(user);
        movieRepo.save(movie);
    }

    public void deleteMovie(String email,int id) throws UnauthorizedActionException {
        Movie movie = movieRepo.getOne(id);
        User user = userRepo.getOne(email);
        if(!user.getEmail().equals(movie.getUser().getEmail()))
            throw new UnauthorizedActionException();
        movieRepo.deleteById(id);
    }
}
