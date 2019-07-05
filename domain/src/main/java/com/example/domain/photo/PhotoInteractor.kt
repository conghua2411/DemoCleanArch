package com.example.domain.photo

import com.example.data.repository.photo.PhotoEntity
import com.example.data.repository.photo.PhotoRemote
import com.example.data.repository.photo.PhotoRepo
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class PhotoInteractor @Inject constructor(
    private val photoRepo: PhotoRepo,
    private val photoRemote: PhotoRemote
) {
    fun getAllPhoto(): Observable<List<PhotoModel>> =
        photoRepo.getAllPhoto().map { t ->
            val list = mutableListOf<PhotoModel>()
            t.forEach {
                list.add(PhotoModel(
                    it.id,
                    it.albumId,
                    it.title,
                    it.url,
                    it.thumbnailUrl))
            }
            list
        }

    fun getPhotoById(id: Long): Single<PhotoModel> =
        photoRepo.getPhotoById(id).map {
            PhotoModel(
                it.id,
                it.albumId,
                it.title,
                it.url,
                it.thumbnailUrl)
        }

    fun savePhoto(list: List<PhotoModel>): Completable =
        photoRepo.savePhotos(list.map {
            PhotoEntity(
                it.id,
                it.albumId,
                it.title,
                it.url,
                it.thumbnailUrl
            )
        })

    fun deletePhoto(photoModel: PhotoModel): Completable =
        photoRepo.deletePhoto(
            PhotoEntity(
                photoModel.id,
                photoModel.albumId,
                photoModel.title,
                photoModel.url,
                photoModel.thumbnailUrl
            )
        )

    fun updatePhoto(photoModel: PhotoModel): Completable =
        photoRepo.updatePhoto(
            PhotoEntity(
                photoModel.id,
                photoModel.albumId,
                photoModel.title,
                photoModel.url,
                photoModel.thumbnailUrl
            )
        )

    fun loadDataApi(): Observable<List<PhotoModel>> =
            photoRemote.getPhotos().map { t ->
                val list = mutableListOf<PhotoModel>()
                t.forEach {
                    list.add(PhotoModel(it.id, it.albumId, it.title, it.url, it.thumbnailUrl))
                }
                list
            }
}