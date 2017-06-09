package com.putraxor.movin.iak.data.review

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by putraxor on 04/06/17.
 */

class ReviewRepository : ReviewDS {

    private val localDS = ReviewLocalDS()
    private val remoteDS = ReviewRemoteDS()

    override fun getReviews(id: Long): Single<List<Review>> = localDS.getReviews(id)
            .onErrorResumeNext {
                remoteDS.getReviews(id)
                        .doOnSuccess { localDS.saveRepositories(id, it) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}