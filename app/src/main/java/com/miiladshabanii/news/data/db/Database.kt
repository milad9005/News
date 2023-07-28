package com.miiladshabanii.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miiladshabanii.news.data.NewsModel
import com.miiladshabanii.news.data.db.dao.NewsDao


@Database(
    entities = [
        NewsModel::class
    ],
    version = 3,
    exportSchema = false
)

abstract class Database :RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "db"
    }

    abstract fun getNewDao():NewsDao
}