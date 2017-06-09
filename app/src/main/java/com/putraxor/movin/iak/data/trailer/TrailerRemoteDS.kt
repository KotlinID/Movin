package com.putraxor.movin.iak.data.trailer


import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rx_object
import com.putraxor.movin.iak.MovinConfig
import com.putraxor.movin.iak.ext.log
import io.reactivex.Single

/**
 * Created by putraxor on 04/06/17.
 */

class TrailerRemoteDS : TrailerDS {

    init {
        FuelManager.Companion.instance.basePath = MovinConfig.API_BASE_URL
        FuelManager.instance.baseParams = listOf("api_key" to MovinConfig.API_KEY)
    }

    override fun getTrailers(id: Long): Single<List<Trailer>> = "/movie/$id/videos"
            .httpGet().log().rx_object(Trailer.ListDeserializer())
            .map { it?.component1() ?: throw it?.component2() ?: throw Exception() }

}