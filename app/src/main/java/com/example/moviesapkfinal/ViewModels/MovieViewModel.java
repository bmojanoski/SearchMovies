package com.example.moviesapkfinal.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.example.moviesapkfinal.Models.Movie;
import com.example.moviesapkfinal.db.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAll();
    }

    public void insert(Movie movie){
        repository.insert(movie);
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }



}
