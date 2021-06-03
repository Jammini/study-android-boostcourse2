package com.example.mymovie.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReviewDao {
    @Insert
    void insertReviews(Review review);

    @Update
    void update(Review review);

    @Query("SELECT * FROM review ORDER BY timestamp DESC")
    List<Review> select();

    @Query("DELETE FROM review")
    void clear();
}
