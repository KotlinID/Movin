package com.putraxor.movin.iak.data.review

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface ReviewDao {

    @Query("SELECT * FROM reviews WHERE movieId=:arg0")
    fun loadReviews(movieId: Long): Flowable<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: MutableList<Review>): Unit
}