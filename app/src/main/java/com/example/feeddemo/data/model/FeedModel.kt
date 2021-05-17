package com.example.feeddemo.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeedModel(
    @SerializedName("rows")
    var rows: List<Row>? = mutableListOf(),
    @SerializedName("title")
    var title: String? = "" // About Canada
) {
    @Entity(tableName = "Feed")
    data class Row(
        @PrimaryKey(autoGenerate = true)
        var id : Int,
        @SerializedName("title")
        var title: String? = "",
        @SerializedName("description")
        var description: String? = "",
        @SerializedName("imageHref")
        var imageHref: String? = ""

    ) : Serializable
}