package com.putraxor.movin.iak.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.putraxor.movin.iak.data.movie.Movie
import com.putraxor.movin.iak.data.movie.MovieDao
import com.putraxor.movin.iak.data.review.Review
import com.putraxor.movin.iak.data.review.ReviewDao
import com.putraxor.movin.iak.data.trailer.Trailer
import com.putraxor.movin.iak.data.trailer.TrailerDao

@Database(entities = arrayOf(Movie::class, Review::class, Trailer::class), version = 1, exportSchema = false)
abstract class MovinDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun reviewDao(): ReviewDao
    abstract fun trailerDao(): TrailerDao

    companion object {
        const val DB_NAME = "movin_db"
    }

}