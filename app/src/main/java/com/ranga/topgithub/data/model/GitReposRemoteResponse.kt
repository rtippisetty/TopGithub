package com.ranga.topgithub.data.model

import com.google.gson.annotations.SerializedName

data class GitReposRemoteResponse(
    @SerializedName("username")
    var username: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("avatar")
    var avatar: String?,

    @SerializedName("url")
    var url: String,

    @SerializedName("repo")
    var repo: Repo?
)

data class Repo(
    @SerializedName("name")
    val username: String?,
    @SerializedName("description")
    val href: String?,
    @SerializedName("url")
    val avatar: String?
)