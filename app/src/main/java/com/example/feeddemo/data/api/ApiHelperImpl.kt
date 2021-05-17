package com.example.feeddemo.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getAllNewsFeedData() = apiService.getAllNewsFeedData()
}