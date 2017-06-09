package com.putraxor.movin.iak

/**
 * Created by putraxor on 05/06/17.
 */
object MovinConfig {
    val API_KEY:String = "6705bca6d690af52****SENSOR****" //TODO: API_KEY ambil dari themoviedb.org
    val API_BASE_URL:String = "http://api.themoviedb.org/3"
    val IMG_BASE_URL:String = "http://image.tmdb.org/t/p/w185"
}

/*
Filtering
http://api.themoviedb.org/3/movie/popular?api_key=
http://api.themoviedb.org/3/movie/top_rated?api_key=

Trailer
http://api.themoviedb.org/3/movie/{id}19404/videos?api_key=
Review
http://api.themoviedb.org/3/movie/{id}22/reviews?api_key=
 */