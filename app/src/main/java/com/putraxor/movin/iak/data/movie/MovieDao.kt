package com.putraxor.movin.iak.data.movie

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun loadPopular(): Flowable<List<Movie>>

    @Query("SELECT * FROM movies ORDER BY voteAverage DESC")
    fun loadTopRated(): Flowable<List<Movie>>

    @Query("SELECT * FROM movies WHERE favorite=1")
    fun loadFavorite(): Flowable<List<Movie>>


    @Query("SELECT * FROM movies WHERE id=:arg0 LIMIT 0,1")
    fun loadById(id: Long): Flowable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: MutableList<Movie>): Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Unit
}