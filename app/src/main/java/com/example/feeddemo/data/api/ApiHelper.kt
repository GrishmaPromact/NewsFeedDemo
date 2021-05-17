package com.example.feeddemo.data.api

import com.example.feeddemo.data.model.FeedModel

interface ApiHelper {

    suspend fun getAllNewsFeedData() : FeedModel
}