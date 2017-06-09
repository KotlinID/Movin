package com.putraxor.movin.iak.data.trailer

import com.putraxor.movin.iak.data.DBMaker
import com.putraxor.movin.iak.ext.mapInPlace
import io.reactivex.Single

class TrailerLocalDS : TrailerDS {
    val dao = DBMaker.db.trailerDao()

    override fun getTrailers(id: Long): Single<List<Trailer>> =
            dao.loadTrailers(id).firstOrError().doOnSuccess { if (it.isEmpty()) throw Exception() }

    override fun saveRepositories(id: Long, list: List<Trailer>) {
        val mutableList = list.toMutableList()
        mutableList.mapInPlace { it.apply{movieId = id} }
        dao.insertAll(mutableList)
    }
}