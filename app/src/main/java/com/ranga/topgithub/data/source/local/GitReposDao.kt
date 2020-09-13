package com.ranga.topgithub.data.source.local

import androidx.room.*
import com.ranga.topgithub.data.source.GitRepo

@Dao
interface GitReposDao {
    @Query("SELECT * FROM GitRepos")
    fun getRepos(): List<GitRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(gitRepos: List<GitRepo>)

    @Query("Delete From GitRepos")
    fun deleteAll()

    @Transaction
    fun updateRepos(gitRepos: List<GitRepo>): Boolean{
        deleteAll()
        insertAll(gitRepos)
        return true
    }
}