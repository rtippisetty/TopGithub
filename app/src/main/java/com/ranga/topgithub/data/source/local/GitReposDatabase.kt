package com.ranga.topgithub.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ranga.topgithub.data.source.GitRepo

@Database(entities = [GitRepo::class], version = 1, exportSchema = false)
abstract class GitReposDatabase: RoomDatabase(){

    abstract fun gitRepoDao(): GitReposDao
}