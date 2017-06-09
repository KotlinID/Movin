package com.putraxor.movin.iak.data.trailer

import io.reactivex.Single

interface TrailerDS {
    fun getTrailers(id: Long): Single<List<Trailer>>
    fun saveRepositories(id:Long, list: List<Trailer>): Unit = Unit
}