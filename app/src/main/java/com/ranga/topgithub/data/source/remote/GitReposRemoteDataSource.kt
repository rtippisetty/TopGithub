package com.ranga.topgithub.data.source.remote

import com.ranga.topgithub.data.source.Result
import com.ranga.topgithub.data.network.GitReposService
import com.ranga.topgithub.data.source.GitRepo
import io.reactivex.Observable

class GitReposRemoteDataSource(private val gitReposService: GitReposService) {
    /**
     * fetch remote git repo details
     *
     * @return Observable of Result type
     */
    fun getRepos(): Observable<List<GitReposRemoteResponse>> {
        return gitReposService.getTopGitRepos()
    }
}