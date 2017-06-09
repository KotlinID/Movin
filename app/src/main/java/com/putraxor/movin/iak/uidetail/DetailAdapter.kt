package com.putraxor.movin.iak.uimovie

import android.view.View
import android.widget.TextView
import com.putraxor.movin.R
import com.putraxor.movin.iak.base.BaseAdapter
import com.putraxor.movin.iak.base.BaseViewHolder
import com.putraxor.movin.iak.data.movie.Movie
import java.text.SimpleDateFormat

/**
 * Created by putraxor on 04/06/17.
 */
class DetailAdapter : BaseAdapter<Movie, DetailAdapter.DetailViewHolder>() {

    override fun getItemViewId(): Int = R.layout.movie_detail_item

    override fun instantiateViewHolder(view: View?): DetailViewHolder = DetailViewHolder(view)

    class DetailViewHolder(itemView: View?) : BaseViewHolder<Movie>(itemView) {

        private val readFormat = SimpleDateFormat("yyyy-MM-dd")
        private val writeFormat = SimpleDateFormat("dd MMMM yyyy")

        val movieTitle: TextView by lazy { itemView?.findViewById(R.id.movieTitle) as TextView }
        val movieDate: TextView by lazy { itemView?.findViewById(R.id.movieDate) as TextView }
        val movieOverview: TextView by lazy { itemView?.findViewById(R.id.movieOverview) as TextView }
        val movieRating: TextView by lazy { itemView?.findViewById(R.id.movieRating) as TextView }
        val moviePopularity: TextView by lazy { itemView?.findViewById(R.id.moviePopularity) as TextView }

        override fun onBind(item: Movie) {
            var formatted = item.releaseDate
            if (item.releaseDate != null) {
                formatted = writeFormat.format(readFormat.parse(item.releaseDate))
            }
            movieTitle.text = item.originalTitle
            movieDate.text = "Release date $formatted"
            movieOverview.text = item.overview
            movieRating.text = "⭐️ Average cote rating ${item.voteAverage}"
            moviePopularity.text = "🔥 Popularity by community ${item.popularity?.toInt()}"
        }
    }

}