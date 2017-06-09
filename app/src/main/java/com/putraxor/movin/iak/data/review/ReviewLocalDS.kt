package com.putraxor.movin.iak.data.review

import com.putraxor.movin.iak.data.DBMaker
import com.putraxor.movin.iak.ext.mapInPlace
import io.reactivex.Single

class ReviewLocalDS : ReviewDS {
    val dao = DBMaker.db.reviewDao()

    override fun getReviews(id: Long): Single<List<Review>> =
            dao.loadReviews(id).firstOrError().doOnSuccess { if (it.isEmpty()) throw Exception() }

    override fun saveRepositories(id: Long, list: List<Review>) {
        val mutableList = list.toMutableList()
        mutableList.mapInPlace { it.apply { movieId = id } }
        dao.insertAll(mutableList)
    }
}