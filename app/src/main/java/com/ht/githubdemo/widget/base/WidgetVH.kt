package com.ht.githubdemo.widget.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ZOMATO on 26,May,2022
 */
class WidgetVH<VIEW, CONFIG: WidgetConfig, VM: WidgetVM> (private val view: VIEW): RecyclerView.ViewHolder(view) where VIEW : WidgetView<CONFIG, VM> {

    fun bindView(widget: Widget<CONFIG, VM>) {
        view.unBind()
        view.bind(widget)
    }

    fun recycleView(position: Int) {
        view.recycle(position)
    }
}