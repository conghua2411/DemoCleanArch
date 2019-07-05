package com.example.democleanarch.di.module

import com.example.data.repository.photo.PhotoRemote
import com.example.data.repository.photo.PhotoRepo
import com.example.data.repository.user.UserDbRepo
import com.example.data.repository.user.UserRemote
import com.example.domain.photo.PhotoInteractor
import com.example.domain.user.UserInteractor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun bindPhotoInteractor(photoRepo: PhotoRepo, photoRemote: PhotoRemote): PhotoInteractor =
            PhotoInteractor(photoRepo, photoRemote)

    @Provides
    fun bindUserInteractor(userDbRepo: UserDbRepo, userRemote: UserRemote): UserInteractor =
            UserInteractor(userDbRepo, userRemote)
}