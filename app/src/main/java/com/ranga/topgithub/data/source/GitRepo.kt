package com.ranga.topgithub.data.source

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ranga.topgithub.data.source.remote.GitReposRemoteResponse

/**
 * model class to hold git repo item details
 *
 * @property username
 * @property name
 * @property url
 * @property avatar
 */
@Entity(tableName = "GitRepos")
data class GitRepo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String?,
    val name: String?,
    val url: String?,
    val avatar: String?
){
    constructor(remoteResponse: GitReposRemoteResponse): this(
        username = remoteResponse.username,
        name = remoteResponse.name,
        url = remoteResponse.url,
        avatar = remoteResponse.avatar)
}