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
    public LiveData<List<Movie>> getAll();

    @Query("DELETE FROM movie_table")
    void deleteAll();

}

