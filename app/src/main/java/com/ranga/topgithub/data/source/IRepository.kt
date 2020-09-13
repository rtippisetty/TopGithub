package com.ranga.topgithub.data.source

import io.reactivex.Observable

interface IRepository {
    fun getRepos(forceUpdate: Boolean): Observable<Result<List<GitRepo>>>
}