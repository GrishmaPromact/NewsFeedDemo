package com.example.feeddemo.data.db

import com.example.feeddemo.data.model.FeedModel


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getAllRows(): List<FeedModel.Row> = appDatabase.feedDao().getAllRowsOfFeed()

    override suspend fun insertAllRows(rows: List<FeedModel.Row>) = appDatabase.feedDao().insertAllRows(rows)

    override suspend fun clearAllRows()  = appDatabase.feedDao().clearTable()

}