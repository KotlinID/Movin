package com.putraxor.movin.iak.data.movie

import io.reactivex.Single

interface MovieDS {

    fun getPopular(): Single<List<Movie>>
    fun getTopRated(): Single<List<Movie>>

    fun saveRepositories(list: List<Movie>) : Unit = Unit

}