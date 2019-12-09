package com.example.moviesapkfinal.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import com.example.moviesapkfinal.Models.Movie;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application){
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAll();
    }

    public void insert(Movie movie){
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }
    public void deleteAll(){
        new DeleteAllMovieAsyncTask(movieDao).execute();
    }
    public LiveData<List<Movie>> getAll(){
        return allMovies;
    }



    public static class InsertMovieAsyncTask extends AsyncTask<Movie,Void,Void>{
        private MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(Movie... movies){
            movieDao.insert(movies[0]);
            return null;
        }
    }

    public static class DeleteAllMovieAsyncTask extends AsyncTask<Void,Void,Void>{
        private MovieDao movieDao;

        private DeleteAllMovieAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(Void... voids){
            movieDao.deleteAll();
            return null;
        }
    }

}
