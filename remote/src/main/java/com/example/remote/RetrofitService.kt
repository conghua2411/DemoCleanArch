package com.example.remote

import com.example.remote.photo.Photo
import com.example.remote.user.User
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitService {
    @GET("/photos")
    fun getPhotos() : Observable<List<Photo>>

    @GET("/users")
    fun getUsers(): Observable<List<User>>
}