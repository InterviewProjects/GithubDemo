package com.ht.githubdemo.base

import com.ht.githubdemo.network.NetworkController
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

/**
 * Created by ZOMATO on 15,June,2022
 */
open class BaseRepo @Inject constructor(val networkController: NetworkController) {
    @Throws(CancellationException::class)
    suspend inline fun <reified T> doGet(api: String, queryMap: Map<String, Any> = HashMap()) = networkController.doGet<T>(api, queryMap)
}