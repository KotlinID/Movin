package com.putraxor.movin.iak.uimovie

import android.arch.lifecycle.MediatorLiveData
import com.putraxor.movin.iak.data.review.Review
import com.putraxor.movin.iak.data.review.ReviewRepository
import io.reactivex.disposables.Disposable

/**
 * Created by putraxor on 04/06/17.
 */

class ReviewLiveData : MediatorLiveData<Pair<List<Review>?, Throwable?>>() {


    val movieRepo = ReviewRepository()

    var movieId: Long? = null
        set(value) {
            value?.let {
                disposable = movieRepo.getReviews(it).subscribe { data, error -> this@ReviewLiveData.value = Pair(data, error) }
            }
        }
    private var disposable: Disposable? = null


    override fun onInactive() {
        super.onInactive()
        if (disposable?.isDisposed?.not() ?: false) {
            disposable?.dispose()
        }
    }

}