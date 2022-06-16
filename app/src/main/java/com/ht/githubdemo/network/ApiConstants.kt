package com.ht.githubdemo.network

/**
 * Created by ZOMATO on 15,June,2022
 */
object ApiConstants {
    const val END_POINT = "https://api.github.com/"

    object Api {
        const val CLOSE_PR = "${END_POINT}repos/%s/%s/pulls"
    }

    object QueryParam {
        const val STATE = "state"
    }

    object Constant {
        const val PR_CLOSED = "close"
    }
}