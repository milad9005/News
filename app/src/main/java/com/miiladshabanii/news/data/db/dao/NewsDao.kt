package com.miiladshabanii.news.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miiladshabanii.news.data.NewsModel
import com.miiladshabanii.news.model.News


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsModel>)

    @Query("DELETE FROM newsmodel")
    fun delete()

    @Query("SELECT * FROM newsmodel")
    fun getNews(): PagingSource<Int, NewsModel>

}