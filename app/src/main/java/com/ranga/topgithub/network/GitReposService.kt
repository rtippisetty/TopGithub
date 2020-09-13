package com.ranga.topgithub.network

import com.ranga.topgithub.common.TopListPeriod
import com.ranga.topgithub.data.model.GitReposRemoteResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitReposService {
    /**
     * API to fetch trending GIT repositories
     * https://github-trending-api.now.sh/developers?language=java&since=weekly
     */
    @GET("/developers")
    fun getTopGitRepos(
        @Query("language") language: String = "java",
        @Query("since") since: String = TopListPeriod.WEEKLY.getValue()
    ): Observable<List<GitReposRemoteResponse>>

}