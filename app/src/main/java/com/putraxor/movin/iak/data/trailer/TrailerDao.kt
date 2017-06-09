package com.putraxor.movin.iak.data.trailer

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface TrailerDao {

    @Query("SELECT * FROM trailers WHERE movieId=:arg0")
    fun loadTrailers(movieId: Long): Flowable<List<Trailer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: MutableList<Trailer>): Unit
}