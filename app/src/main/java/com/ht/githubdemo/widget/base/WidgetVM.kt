package com.ht.githubdemo.widget.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Created by ZOMATO on 26,May,2022
 */
abstract class WidgetVM(app: Application, var widgetNotifyCallback: WidgetNotifyCallback? = null): AndroidViewModel(app) {
    interface WidgetNotifyCallback {
        fun notify(action: String, data: Any?)
    }
}