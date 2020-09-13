package com.ranga.topgithub.data.source.local

import com.ranga.topgithub.data.source.GitRepo
import com.ranga.topgithub.data.source.Result
import io.reactivex.Observable

class GitReposLocalDataSource internal constructor(
    private val gitReposDao: GitReposDao
) {

    /**
     * fetch local git repo details
     *
     * @return Observable of Result type
     */
    fun getRepos(): Observable<Result<List<GitRepo>>> {
        return try {
            Observable.just(Result.Success(gitReposDao.getRepos()))
        } catch (e: Exception) {
            Observable.just(Result.Error(e))
        }
    }

    fun updateRepos(repoList: List<GitRepo>): Observable<Result<Boolean>>{
        return try {
            Observable.just(Result.Success(gitReposDao.updateRepos(repoList)))
        } catch (e: Exception) {
            Observable.just(Result.Error(e))
        }
    }
}