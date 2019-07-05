package com.example.data.repository.user

import io.reactivex.Observable

interface UserRemote {
    fun getUsersRemote() : Observable<List<UserEntity>>
}