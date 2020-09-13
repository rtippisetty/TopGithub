package com.ranga.topgithub.data.manager

import com.ranga.topgithub.data.model.GitRepo
import com.ranga.topgithub.data.model.Result
import io.reactivex.Observable

interface IRepository {
    fun getRepos(forceUpdate: Boolean): Observable<Result<List<GitRepo>>>
}