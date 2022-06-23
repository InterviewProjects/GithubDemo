package com.ht.githubdemo.base

import androidx.lifecycle.ViewModel
import com.ht.githubdemo.BuildConfig
import com.ht.githubdemo.utils.SingleLiveEvent
import com.ht.githubdemo.widget.base.WidgetVM
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

/**
 * Created by ZOMATO on 15,June,2022
 */
open class BaseVM: ViewModel(), WidgetVM.WidgetNotifyCallback {
    val loaderLE = SingleLiveEvent<Boolean>()
    val toastLE = SingleLiveEvent<String>()
    val errorLE = SingleLiveEvent<String?>()

    protected val coroutineContext = Dispatchers.Default + CoroutineExceptionHandler{ _, th ->
        if (BuildConfig.DEBUG) { th.printStackTrace() }
    }

    override fun notify(action: String, data: Any?) {}
}