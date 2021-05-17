package com.example.feeddemo.data.api

import com.example.feeddemo.data.model.FeedModel
import retrofit2.http.GET

interface ApiService {

    @GET("facts.json")
    suspend fun getAllNewsFeedData() : FeedModel
}