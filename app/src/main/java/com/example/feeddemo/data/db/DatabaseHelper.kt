package com.example.feeddemo.data.db

import com.example.feeddemo.data.model.FeedModel


interface DatabaseHelper {

    suspend fun getAllRows() : List<FeedModel.Row>

    suspend fun insertAllRows(rows : List<FeedModel.Row>)

    suspend fun clearAllRows()

}