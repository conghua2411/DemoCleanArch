package com.example.cache.db.photo

import com.example.cache.EntityModelMapper
import com.example.data.repository.photo.PhotoEntity
import com.example.data.repository.photo.PhotoRepo
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepoImpl @Inject constructor(private val photoDAO: PhotoDAO) : PhotoRepo,
    EntityModelMapper<PhotoEntity, Photo> {

    override fun getAllPhoto(): Observable<List<PhotoEntity>> =
        Observable.fromCallable { photoDAO.getPhotos() }.map { t ->
            mapModelToEntity(t)
        }

    override fun getPhotoById(id: Long): Single<PhotoEntity> =
        Observable.fromCallable { photoDAO.getPhotosById(id) }.map { t ->
            mapModelToEntity(t)
        }
            .singleOrError()

    override fun savePhotos(photos: List<PhotoEntity>): Completable =
        Completable.defer {
            photos.forEach {
                photoDAO.insert(mapEntityToModel(it))
            }
            Completable.complete()
        }

    override fun deletePhoto(photoEntity: PhotoEntity): Completable =
        Completable.defer {
            photoDAO.delete(mapEntityToModel(photoEntity))
            Completable.complete()
        }

    override fun updateDataPhoto(photos: List<PhotoEntity>): Completable =
        Completable.defer {
            photoDAO.updateData(mapEntityToModel(photos))
            Completable.complete()
        }

    override fun updatePhoto(photoEntity: PhotoEntity): Completable =
        Completable.defer {
            photoDAO.update(mapEntityToModel(photoEntity))
            Completable.complete()
        }

    override fun mapEntityToModel(e: PhotoEntity): Photo =
        Photo(e.id, e.albumId, e.title, e.url, e.thumbnailUrl)

    override fun mapEntityToModel(e: List<PhotoEntity>): List<Photo> = e.map {
        mapEntityToModel(it)
    }


    override fun mapModelToEntity(m: Photo): PhotoEntity =
        PhotoEntity(m.id, m.albumId, m.title, m.url, m.thumbnailUrl)


    override fun mapModelToEntity(m: List<Photo>): List<PhotoEntity> = m.map {
        mapModelToEntity(it)
    }

}