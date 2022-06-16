package com.ht.githubdemo.pojos

import com.google.gson.annotations.SerializedName
import com.ht.githubdemo.utils.Utils

/**
 * Created by ZOMATO on 15,June,2022
 */

data class ClosePRItem(
    @SerializedName("title") val title: String?,
    @SerializedName("created_at") private val _createdAt: String?,
    @SerializedName("closed_at") private val _closedAt: String?,
    @SerializedName("user") val user: GitUser?
) {
    val createdAt get() = Utils.getDate(_createdAt, Utils.INPUT_DATE_FORMAT_TYPE, Utils.OUTPUT_DATE_FORMAT_TYPE)
    val closedAt get() = Utils.getDate(_closedAt, Utils.INPUT_DATE_FORMAT_TYPE, Utils.OUTPUT_DATE_FORMAT_TYPE)
}

data class GitUser(
    @SerializedName("login") val userName: String?,
    @SerializedName("avatar_url") val userImage: String?
)