package com.ht.githubdemo.widget.base

import android.content.Context

/**
 * Created by ZOMATO on 26,May,2022
 */
abstract class WidgetConfig {
    abstract fun viewType(): Int
    abstract fun view(): (Context) -> WidgetView<*, *>

    open fun areItemsTheSame(oldWidgetConfig: WidgetConfig) = this.viewType() == oldWidgetConfig.viewType()
    open fun areContentsTheSame(oldWidgetConfig: WidgetConfig) = this == oldWidgetConfig

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}