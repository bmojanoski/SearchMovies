package com.example.moviesapkfinal.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import com.example.moviesapkfinal.Models.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * from movie_table")
     LiveData<List<Movie>> getAll();

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * FROM movie_table WHERE imdbID == :imdbID")
     Movie getMovie(String imdbID);

}

