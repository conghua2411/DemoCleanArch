package com.example.democleanarch.di.module

import android.app.Application
import androidx.room.Room
import com.example.cache.db.CleanArchDatabase
import com.example.cache.db.photo.PhotoRepoImpl
import com.example.cache.db.user.UserRepoImpl
import com.example.data.repository.photo.PhotoRepo
import com.example.data.repository.user.UserDbRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Provides
    @Singleton
    fun provideCleanArchDatabase(application: Application): CleanArchDatabase =
        Room.databaseBuilder(
            application,
            CleanArchDatabase::class.java,
            "CleanArchDatabase.db"
        )
            .build()

    //repo
    @Provides
    @Singleton
    fun providePhotoRepo(cleanArchDatabase: CleanArchDatabase): PhotoRepo =
        PhotoRepoImpl(cleanArchDatabase.photoDao())

    @Provides
    @Singleton
    fun provideUserRepo(cleanArchDatabase: CleanArchDatabase): UserDbRepo =
            UserRepoImpl(cleanArchDatabase.userDao())
}