package com.ht.githubdemo.widget.widgetloadmore

import com.ht.githubdemo.R
import com.ht.githubdemo.widget.base.WidgetConfig

/**
 * Created by ZOMATO on 15,June,2022
 */
class WidgetLoadMoreConfig: WidgetConfig() {
    override fun viewType() = R.layout.item_widget_load_more
    override fun view() = ::WidgetLoadMoreView

    override fun areItemsTheSame(oldWidgetConfig: WidgetConfig) = this == oldWidgetConfig
    override fun areContentsTheSame(oldWidgetConfig: WidgetConfig) = this == oldWidgetConfig
}