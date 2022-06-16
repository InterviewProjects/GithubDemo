package com.ht.githubdemo.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ht.githubdemo.base.BaseRepo
import com.ht.githubdemo.base.BaseVM
import com.ht.githubdemo.network.ApiConstants.QueryParam
import com.ht.githubdemo.network.ApiConstants.Api
import com.ht.githubdemo.network.ApiConstants.Constant
import com.ht.githubdemo.pojos.ClosePRItem
import com.ht.githubdemo.utils.SingleLiveEvent
import com.ht.githubdemo.widget.base.Widget
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by ZOMATO on 15,June,2022
 */
class HomeVM(app: Application): BaseVM(app) {
    private val repo = BaseRepo()
    private val intr = HomeIntr()

    val adapterListLE = SingleLiveEvent<List<Widget<*, *>>>()

    fun resume() {
        /** show loader */
        loaderLE.value = true

        viewModelScope.launch(coroutineContext) {
            val userName = "android-async-http"
            val repository = "android-async-http"
            val closePRApi = String.format(Api.CLOSE_PR, userName, repository)

            val output = repo.doGet<List<ClosePRItem>>(
                api = closePRApi,
                queryMap = hashMapOf( QueryParam.STATE to Constant.PR_CLOSED )
            )

            if (output.isSuccessful) {
                val widgets = intr.getWidgets(closedPRs = output.data)
                adapterListLE.postValue(widgets)
            } else {
                toastLE.postValue(output.error?.msg)
            }

            /** hide loader */
            loaderLE.postValue(false)
        }
    }
}