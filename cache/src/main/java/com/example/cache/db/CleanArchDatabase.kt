package com.example.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.db.photo.PhotoDAO
import com.example.cache.db.photo.Photo
import com.example.cache.db.user.User
import com.example.cache.db.user.UserDAO

@Database(entities = [Photo::class, User::class], version = 1, exportSchema = false)
abstract class CleanArchDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDAO
    abstract fun userDao(): UserDAO
}