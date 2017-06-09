package com.putraxor.movin.iak.uimovie

import android.arch.lifecycle.MediatorLiveData
import com.putraxor.movin.iak.data.movie.Movie
import com.putraxor.movin.iak.data.movie.MovieRepository
import io.reactivex.disposables.Disposable

/**
 * Created by putraxor on 04/06/17.
 */

class DetailLiveData : MediatorLiveData<Pair<List<Movie>?, Throwable?>>() {


    val movieRepo = MovieRepository()
    var movieId: Long? = null
        set(value) {
            value?.let {
                disposable = movieRepo.getById(it).subscribe { data, error -> this@DetailLiveData.value = Pair(data, error) }
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