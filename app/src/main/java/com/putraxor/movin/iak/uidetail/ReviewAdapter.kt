package com.putraxor.movin.iak.uimovie

import android.view.View
import android.widget.TextView
import com.putraxor.movin.R
import com.putraxor.movin.iak.base.BaseAdapter
import com.putraxor.movin.iak.base.BaseViewHolder
import com.putraxor.movin.iak.data.review.Review

/**
 * Created by putraxor on 04/06/17.
 */
class ReviewAdapter : BaseAdapter<Review, ReviewAdapter.ReviewViewHolder>() {

    override fun getItemViewId(): Int = R.layout.review_item

    override fun instantiateViewHolder(view: View?): ReviewViewHolder = ReviewViewHolder(view)

    class ReviewViewHolder(itemView: View?) : BaseViewHolder<Review>(itemView) {

        val author: TextView by lazy { itemView?.findViewById(R.id.author) as TextView }
        val content: TextView by lazy { itemView?.findViewById(R.id.content) as TextView }

        override fun onBind(item: Review) {
            author.text = item.author
            content.text = item.content
        }
    }

}