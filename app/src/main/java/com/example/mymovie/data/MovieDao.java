package com.example.mymovie.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insertMovie(Movie movie);

    @Update
    void update(Movie movie);

    @Query("SELECT * FROM Movies WHERE id = :id")
    Movie selectMovieDetail(int id);

    @Query("SELECT * FROM Movies")
    List<Movie> selectMovies();

    @Query("DELETE FROM Movies")
    void clear();
}
