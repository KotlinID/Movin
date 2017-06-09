package com.putraxor.movin.iak.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by putraxor on 04/06/17.
 */
abstract class BaseViewHolder<in D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: D)
}