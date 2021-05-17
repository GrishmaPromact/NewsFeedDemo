package com.example.feeddemo.utils

import androidx.room.TypeConverter
import com.example.feeddemo.data.model.FeedModel
import com.google.gson.Gson

/**
 * Converter class
 */
class Converters {
    @TypeConverter
    fun listToJson(value: List<FeedModel.Row>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<FeedModel.Row>::class.java).toList()
}