package com.ht.githubdemo.widget.base

import android.content.Context
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner

/**
 * Created by ZOMATO on 26,May,2022
 */


abstract class WidgetView<Config: WidgetConfig, VM: WidgetVM> constructor(context: Context): FrameLayout(context, null, 0) {
    var lifecycleOwner: LifecycleOwner? = null

    protected var widget: Widget<Config, VM>? = null
    open fun bind(widget: Widget<Config, VM>?) {
        this.widget = widget
    }
    open fun unBind() {}

    open fun recycle(position: Int) = unBind()
}