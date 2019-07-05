package com.example.cache.db.photo

import androidx.room.*
import com.example.cache.Constants
import com.example.cache.db.base.BaseDAO

@Dao
abstract class PhotoDAO : BaseDAO<Photo> {

    @Query("SELECT * FROM ${Constants.PHOTO_TABLE_NAME} LIMIT 20")
    abstract fun getPhotos(): List<Photo>

    @Query("SELECT * FROM ${Constants.PHOTO_TABLE_NAME} WHERE id= :id")
    abstract fun getPhotosById(id: Long): Photo

    @Query("DELETE FROM ${Constants.PHOTO_TABLE_NAME}")
    abstract fun deleteAllPhoto()

    @Transaction
    open fun updateData(photos: List<Photo>) {
        deleteAllPhoto()
        updateData(photos)
    }
}