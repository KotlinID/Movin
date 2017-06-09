package com.putraxor.movin.iak.data.review

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.putraxor.movin.iak.ext.getResultJson

/**
 * Created by putraxor on 05/06/17.
 */

@Entity(tableName = "reviews")
class Review {
    @PrimaryKey
    var id: String? = null
    var movieId: Long? = null
    var author: String? = null
    var content: String? = null

    class ListDeserializer : ResponseDeserializable<List<Review>> {
        override fun deserialize(content: String): List<Review> =
                Gson().fromJson<List<Review>>(content.getResultJson(), object : TypeToken<List<Review>>() {}.type)
    }
}