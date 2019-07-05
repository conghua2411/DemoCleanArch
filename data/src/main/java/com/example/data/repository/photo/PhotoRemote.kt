package com.example.data.repository.photo

import io.reactivex.Observable

interface PhotoRemote {
    fun getPhotos() : Observable<List<PhotoEntity>>
}