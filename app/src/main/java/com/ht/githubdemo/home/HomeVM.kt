package com.ht.githubdemo.home

import androidx.lifecycle.viewModelScope
import com.ht.githubdemo.base.BaseRepo
import com.ht.githubdemo.base.BaseVM
import com.ht.githubdemo.network.ApiConstants.QueryParam
import com.ht.githubdemo.network.ApiConstants.Api
import com.ht.githubdemo.network.ApiConstants.Constant
import com.ht.githubdemo.pojos.ClosePRItem
import com.ht.githubdemo.utils.SingleLiveEvent
import com.ht.githubdemo.widget.base.Widget
import com.ht.githubdemo.widget.widgetloadmore.WidgetLoadMoreConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ZOMATO on 15,June,2022
 */
@HiltViewModel
class HomeVM @Inject constructor(private val repo: BaseRepo, private val intr: HomeIntr): BaseVM() {
    private val userName = "android-async-http"
    private val repository = "android-async-http"

    val adapterListLE = SingleLiveEvent<List<Widget<*, *>>?>()
    private val rawList = mutableListOf<Widget<*, *>>()

    fun resume() {
        /** show loader */
        loaderLE.value = true

        /** hide error screen */
        errorLE.value = null

        /** reset the no products view */
        rawList.clear()
        adapterListLE.postValue(null)

        viewModelScope.launch(coroutineContext) {
            val closePRApi = String.format(Api.CLOSE_PR, userName, repository)

            val output = repo.doGet<List<ClosePRItem>>(
                api = closePRApi,
                queryMap = hashMapOf( QueryParam.STATE to Constant.PR_CLOSED )
            )

            if (output.isSuccessful) {
                val widgets = intr.getWidgets(closedPRs = output.data)

                rawList.addAll(widgets)
                adapterListLE.postValue(rawList.toMutableList())
            } else {
                errorLE.postValue(output.error?.msg)
            }

            /** hide loader */
            loaderLE.postValue(false)
        }
    }

    /** page scroll */
    fun loadMore(pageNo: Int) {
        viewModelScope.launch(coroutineContext) {
            /** show load more */
            rawList.add(Widget(config = WidgetLoadMoreConfig(), vm = null))
            adapterListLE.postValue(rawList.toMutableList())

            val closePRApi = String.format(Api.CLOSE_PR, userName, repository)

            val output = repo.doGet<List<ClosePRItem>>(
                api = closePRApi,
                queryMap = hashMapOf( QueryParam.STATE to Constant.PR_CLOSED, QueryParam.PAGE to pageNo )
            )

            /** hide load more */
            rawList.removeAt(rawList.size - 1)

            if (output.isSuccessful) {
                val widgets = intr.getWidgets(closedPRs = output.data)

                rawList.addAll(widgets)
                adapterListLE.postValue(rawList.toMutableList())
            } else {
                errorLE.postValue(output.error?.msg)
            }
        }
    }
}