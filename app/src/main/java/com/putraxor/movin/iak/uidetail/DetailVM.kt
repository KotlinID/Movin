package com.putraxor.movin.iak.uimovie

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.putraxor.movin.iak.data.movie.Movie
import com.putraxor.movin.iak.data.review.Review
import com.putraxor.movin.iak.data.trailer.Trailer

/**
 * Created by putraxor on 04/06/17.
 */
class DetailVM(application: Application?) : AndroidViewModel(application) {

    private val filterLiveData = MutableLiveData<Long>()

    val detailLD = DetailLiveData()
    val reviewLD = ReviewLiveData()
    val trailerLD = TrailerLiveData()

    val isLoadingLiveData = MediatorLiveData<Boolean>()
    val throwableLiveData = MediatorLiveData<Throwable>()
    val detailRepoLD = MediatorLiveData<List<Movie>>()
    val reviewRepoLD = MediatorLiveData<List<Review>>()
    val trailerRepoLD = MediatorLiveData<List<Trailer>>()

    init {
        detailLD.addSource(filterLiveData) { it?.let {
            detailLD.movieId = it
            reviewLD.movieId = it
            trailerLD.movieId = it
        } }
        //reviewLD.addSource(filterLiveData) { it?.let { reviewLD.movieId = it } }
        //trailerLD.addSource(filterLiveData) { it?.let { trailerLD.movieId = it } }

        isLoadingLiveData.addSource(detailLD) {
            isLoadingLiveData.value = false
        }
        throwableLiveData.addSource(detailLD) {
            it?.second?.let { throwableLiveData.value = it }
        }
        detailRepoLD.addSource(detailLD) {
            it?.first?.let { detailRepoLD.value = it }
        }

        isLoadingLiveData.addSource(reviewLD) {
            isLoadingLiveData.value = false
        }
        throwableLiveData.addSource(reviewLD) {
            it?.second?.let { throwableLiveData.value = it }
        }
        reviewRepoLD.addSource(reviewLD) {
            it?.first?.let { reviewRepoLD.value = it }
        }
        isLoadingLiveData.addSource(trailerLD) {
            isLoadingLiveData.value = false
        }
        throwableLiveData.addSource(trailerLD) {
            it?.second?.let { throwableLiveData.value = it }
        }
        trailerRepoLD.addSource(trailerLD) {
            it?.first?.let { trailerRepoLD.value = it }
        }
    }

    fun setFilter(filterMovie: Long) {
        filterLiveData.value = filterMovie
        isLoadingLiveData.value = true
    }
}