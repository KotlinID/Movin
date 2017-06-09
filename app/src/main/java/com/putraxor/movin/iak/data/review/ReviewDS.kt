package com.putraxor.movin.iak.data.review

import io.reactivex.Single

interface ReviewDS {
    fun getReviews(id: Long): Single<List<Review>>
    fun saveRepositories(id:Long, list: List<Review>): Unit = Unit
}