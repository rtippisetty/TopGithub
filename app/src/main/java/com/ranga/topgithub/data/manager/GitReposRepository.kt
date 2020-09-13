package com.ranga.topgithub.data.manager

import com.ranga.topgithub.data.local.GitReposLocalDataSource
import com.ranga.topgithub.data.model.GitRepo
import com.ranga.topgithub.data.model.Result
import com.ranga.topgithub.data.model.succeeded
import com.ranga.topgithub.data.remote.GitReposRemoteDataSource
import com.ranga.topgithub.di.ApplicationModule.ReposLocalDataSource
import com.ranga.topgithub.di.ApplicationModule.ReposRemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject


class GitReposRepository @Inject constructor(
    @ReposLocalDataSource private val localDataSource: GitReposLocalDataSource,
    @ReposRemoteDataSource private val remoteDataSource: GitReposRemoteDataSource
): IRepository {

    override fun getRepos(forceUpdate: Boolean): Observable<Result<List<GitRepo>>> {
        if(!forceUpdate){
            return localDataSource.getRepos()
        } else {
            return remoteDataSource.getRepos().flatMap { response ->
                if(response.isEmpty()){
                    Observable.just(Result.Error(Exception("Unable to fetch data")))
                } else{
                    localDataSource.updateRepos(response.map { GitRepo(it) })
                }
            }.flatMap { result ->
                if(result.succeeded){
                    localDataSource.getRepos()
                }else {
                    Observable.just(Result.Error(Exception("Unable to update data")))
                }
            }
        }
    }
}