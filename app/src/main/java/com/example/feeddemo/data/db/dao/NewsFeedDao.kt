package com.example.feeddemo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.feeddemo.data.model.FeedModel

@Dao
interface NewsFeedDao {

    @Query("SELECT * FROM feed")
    suspend fun getAllRowsOfFeed() : List<FeedModel.Row>

    @Insert
    suspend fun insertAllRows(rows : List<FeedModel.Row>)

    @Query("DELETE FROM feed")
    suspend fun clearTable()

}