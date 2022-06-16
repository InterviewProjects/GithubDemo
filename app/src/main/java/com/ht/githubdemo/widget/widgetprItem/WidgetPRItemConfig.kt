package com.ht.githubdemo.widget.widgetprItem

import com.ht.githubdemo.R
import com.ht.githubdemo.pojos.ClosePRItem
import com.ht.githubdemo.widget.base.WidgetConfig

/**
 * Created by ZOMATO on 15,June,2022
 */
data class WidgetPRItemConfig(val prItem: ClosePRItem): WidgetConfig() {
    override fun viewType() = R.layout.item_widget_pr_item
    override fun view() = ::WidgetPRItemView

    override fun areItemsTheSame(oldWidgetConfig: WidgetConfig) = this.prItem.user?.userName == (oldWidgetConfig as? WidgetPRItemConfig)?.prItem?.user?.userName
    override fun areContentsTheSame(oldWidgetConfig: WidgetConfig) = this == oldWidgetConfig
}