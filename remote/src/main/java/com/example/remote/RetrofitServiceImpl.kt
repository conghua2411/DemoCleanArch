package com.example.remote

import com.example.data.repository.photo.PhotoEntity
import com.example.data.repository.photo.PhotoRemote
import com.example.data.repository.user.UserEntity
import com.example.data.repository.user.UserRemote
import io.reactivex.Observable
import javax.inject.Inject

class RetrofitServiceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    PhotoRemote {
    override fun getPhotos(): Observable<List<PhotoEntity>> {
        return retrofitService.getPhotos().map { t ->
            val list = mutableListOf<PhotoEntity>()
            t.forEach {
                list.add(
                    PhotoEntity(
                        it.id,
                        it.albumId,
                        it.title,
                        it.url,
                        it.thumbnailUrl
                    )
                )
            }
            list
        }
    }
}