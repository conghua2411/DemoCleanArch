package com.example.data.repository.photo

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface PhotoRepo {
    fun getAllPhoto(): Observable<List<PhotoEntity>>

    fun getPhotoById(id: Long): Single<PhotoEntity>

    fun savePhotos(photos: List<PhotoEntity>): Completable

    fun deletePhoto(photoEntity: PhotoEntity): Completable

    fun updatePhoto(photoEntity: PhotoEntity): Completable

    fun updateDataPhoto(photos: List<PhotoEntity>): Completable
}