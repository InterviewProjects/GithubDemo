package com.ht.githubdemo.network

import com.google.gson.internal.LinkedTreeMap
import com.ht.githubdemo.utils.Ext.toGsonString
import com.ht.githubdemo.utils.Ext.toObject
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

/**
 * Created by ZOMATO on 15,June,2022
 */

class NetworkController @Inject constructor(val apiService: ApiService) {
    @Throws(CancellationException::class)
    suspend inline fun <reified T>doGet(api: String, queryMap: Map<String, Any> = HashMap()): ApiResponse<T> {
        val output = try {
            val response = apiService.get<LinkedTreeMap<String, String>>(api, queryMap)
            handleApiResponse(response, api)
        } catch (ex: Exception) {
            ApiResponse<T>(data = null, error = ApiError(msg = ex.message))
        }

        return output
    }

    inline fun <reified T>handleApiResponse(response: Response<LinkedTreeMap<String, String>>, requestType: String): ApiResponse<T> {
        val returnValue: ApiResponse<T> = when {
            response.isSuccessful -> {
                val data = response.body().toGsonString().toObject<T>()
                ApiResponse(data = data, error = null)
            }

            else -> {
                val errorString = if (response.errorBody() != null) response.errorBody()!!.string() else "Something went wrong"
                val error = ApiError(msg = errorString)
                ApiResponse(data = null, error = error)
            }
        }

        return returnValue
    }
}

data class ApiResponse <T> (
    val data: T? = null,
    val error: ApiError? = null
) {
    val isSuccessful = data != null
}

data class ApiError(
    val msg: String? = null
)