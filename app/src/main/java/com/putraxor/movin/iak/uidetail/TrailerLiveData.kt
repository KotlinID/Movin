package com.putraxor.movin.iak.uimovie

import android.arch.lifecycle.MediatorLiveData
import com.putraxor.movin.iak.data.trailer.Trailer
import com.putraxor.movin.iak.data.trailer.TrailerRepository
import io.reactivex.disposables.Disposable

/**
 * Created by putraxor on 04/06/17.
 */

class TrailerLiveData : MediatorLiveData<Pair<List<Trailer>?, Throwable?>>() {


    val repo = TrailerRepository()

    var movieId: Long? = null
        set(value) {
            value?.let {
                disposable = repo.getTrailers(it).subscribe { data, error -> this@TrailerLiveData.value = Pair(data, error) }
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