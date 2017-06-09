package com.putraxor.movin.iak.data.movie

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by putraxor on 04/06/17.
 */

class MovieRepository : MovieDS {

    private val localDS = MovieLocalDS()
    private val remoteDS = MovieRemoteDS()

    override fun getPopular(): Single<List<Movie>> = localDS.getPopular()
            .doOnSuccess {
                remoteDS.getPopular()
                        .doOnSuccess { localDS.saveRepositories(it) }
            }
            .onErrorResumeNext {
                remoteDS.getPopular()
                        .doOnSuccess { localDS.saveRepositories(it) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getTopRated(): Single<List<Movie>> = localDS.getTopRated()
            .onErrorResumeNext {
                remoteDS.getPopular()
                        .doOnSuccess { localDS.saveRepositories(it) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getFavorite(): Single<List<Movie>> = localDS.getFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getById(id:Long): Single<List<Movie>> = localDS.getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}