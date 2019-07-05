package com.example.domain.photo


data class PhotoModel(
    val id: Long?,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)