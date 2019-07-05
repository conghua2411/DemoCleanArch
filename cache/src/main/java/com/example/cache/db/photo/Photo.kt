package com.example.cache.db.photo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cache.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.PHOTO_TABLE_NAME)
data class Photo(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long?,
    @SerializedName("albumId")
    val albumId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)