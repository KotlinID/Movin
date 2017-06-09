package com.putraxor.movin.iak.data.trailer

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by putraxor on 04/06/17.
 */

class TrailerRepository : TrailerDS {

    private val localDS = TrailerLocalDS()
    private val remoteDS = TrailerRemoteDS()

    override fun getTrailers(id: Long): Single<List<Trailer>> = localDS.getTrailers(id)
            .onErrorResumeNext {
                remoteDS.getTrailers(id)
                        .doOnSuccess { localDS.saveRepositories(id, it) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}