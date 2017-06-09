package com.putraxor.movin.iak.data.movie

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.putraxor.movin.iak.ext.getResultJson

/**
 * Created by putraxor on 05/06/17.
 */

@Entity(tableName = "movies")
open class Movie {
    @PrimaryKey
    var id: Long? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    var overview: String? = null
    @SerializedName("vote_average")
    var voteAverage: Double? = null
    var popularity: Double? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    var favorite: Int? = 0

    class ListDeserializer : ResponseDeserializable<List<Movie>> {
        override fun deserialize(content: String): List<Movie> =
                Gson().fromJson<List<Movie>>(content.getResultJson(), object : TypeToken<List<Movie>>() {}.type)
    }
}
