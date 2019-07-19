package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.SecondaryTable;
import java.util.HashSet;
import java.util.Set;

@Controller
public class homecontroller {
    @Autowired
    ActorRepoistory actorRepoistory;
    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model){
        //First let's create an actor
        Actor actor = new Actor ();
        actor.setName("Sandara Bullock");
        actor.setRealname("Sandara Mae Bullwoski");
        //Create a movie
        Movie movie = new Movie();
        movie.setTitle("Emoji Movie");
        movie.setYear(2017);
        movie.setDescription("About Emojis..");

        //add movie into empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        //add the list of movies to the actor's movie list
        actor.setMovies(movies);

        //save the actor to the database
        actorRepoistory.save(actor);

        //grab the actors from the datebase and send them to the template

        model.addAttribute("actors", actorRepoistory.findAll());
        return "index";
    }
}
