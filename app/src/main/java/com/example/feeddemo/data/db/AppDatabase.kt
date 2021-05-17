package com.example.feeddemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feeddemo.data.db.dao.NewsFeedDao
import com.example.feeddemo.data.model.FeedModel
import com.example.feeddemo.utils.Converters

@Database(entities = [FeedModel.Row::class], version = 1 , exportSchema = false)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feedDao(): NewsFeedDao
}