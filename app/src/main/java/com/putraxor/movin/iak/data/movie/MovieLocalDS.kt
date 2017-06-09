package com.putraxor.movin.iak.data.movie

import com.putraxor.movin.iak.data.DBMaker
import io.reactivex.Single

class MovieLocalDS : MovieDS {

    val dao = DBMaker.db.movieDao()

    override fun getPopular(): Single<List<Movie>>
            = dao.loadPopular().firstOrError().doOnSuccess { if (it.isEmpty()) throw Exception() }

    override fun getTopRated(): Single<List<Movie>>
            = dao.loadTopRated().firstOrError().doOnSuccess { if (it.isEmpty()) throw Exception() }

    fun getFavorite(): Single<List<Movie>>
            = dao.loadFavorite().firstOrError().doOnSuccess { if (it.isEmpty()) throw Exception() }

    fun getById(id: Long): Single<List<Movie>>
            = dao.loadById(id).firstOrError().doOnSuccess { if (it.isEmpty()) throw Exception() }


    override fun saveRepositories(list: List<Movie>) = dao.insertAll(list.toMutableList())

    fun saveMovie(movie: Movie) = dao.insertMovie(movie)
}