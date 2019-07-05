package com.example.democleanarch.di.module

import com.example.data.repository.photo.PhotoRemote
import com.example.data.repository.user.UserRemote
import com.example.remote.RetrofitService
import com.example.remote.RetrofitServiceFactory
import com.example.remote.RetrofitServiceImpl
import com.example.remote.user.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {
//    @Module
//    companion object {
//        @Provides
//        fun provideRetrofitService(): RetrofitService =
//            RetrofitServiceFactory.makeRetrofitService()
//    }

    @Provides
    fun provideRetrofitService(): RetrofitService =
        RetrofitServiceFactory.makeRetrofitService()

    @Provides
    fun bindPhotoRemote(retrofitService: RetrofitService): PhotoRemote =
        RetrofitServiceImpl(retrofitService)

    @Provides
    fun bindUserRemote(retrofitService: RetrofitService): UserRemote =
        UserServiceImpl(retrofitService)

}