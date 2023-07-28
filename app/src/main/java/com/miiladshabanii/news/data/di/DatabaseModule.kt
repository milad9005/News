package com.miiladshabanii.news.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miiladshabanii.news.data.db.Database
import com.miiladshabanii.news.data.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun databaseProvider(
        @ApplicationContext
        context: Context
    ): Database =
        Room.databaseBuilder(
            context = context,
            Database::class.java,
            name = Database.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun newsDao(
        db: Database
    ): NewsDao = db.getNewDao()
}