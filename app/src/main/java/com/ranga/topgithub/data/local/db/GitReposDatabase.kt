package com.ranga.topgithub.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ranga.topgithub.data.local.db.GitReposDao
import com.ranga.topgithub.data.model.GitRepo

@Database(entities = [GitRepo::class], version = 1, exportSchema = false)
abstract class GitReposDatabase: RoomDatabase(){

    abstract fun gitRepoDao(): GitReposDao
}