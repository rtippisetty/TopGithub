package com.ranga.topgithub.data.remote

import com.ranga.topgithub.data.model.GitReposRemoteResponse
import com.ranga.topgithub.network.GitReposService
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