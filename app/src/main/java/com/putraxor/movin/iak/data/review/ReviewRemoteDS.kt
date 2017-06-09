package com.putraxor.movin.iak.data.review


import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rx_object
import com.putraxor.movin.iak.MovinConfig
import com.putraxor.movin.iak.ext.log
import io.reactivex.Single

/**
 * Created by putraxor on 04/06/17.
 */

class ReviewRemoteDS : ReviewDS {

    init {
        FuelManager.Companion.instance.basePath = MovinConfig.API_BASE_URL
        FuelManager.instance.baseParams = listOf("api_key" to MovinConfig.API_KEY)
    }

    override fun getReviews(id: Long): Single<List<Review>> = "/movie/$id/reviews"
                    .httpGet().log().rx_object(Review.ListDeserializer())
                    .map { it?.component1() ?: throw it?.component2() ?: throw Exception() }

}