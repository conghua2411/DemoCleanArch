package com.example.data.repository.user

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface UserDbRepo {
    fun getUsers(): Observable<List<UserEntity>>

    fun getUsersFlow(): Flowable<List<UserEntity>>

    fun getUserById(id: Long): Observable<UserEntity>

    fun saveUsers(list: List<UserEntity>): Completable

    fun updateUser(userEntity: UserEntity): Completable

    fun deleteUser(userEntity: UserEntity): Completable

    fun deleteAllUsers(): Completable

    fun updateDataUser(users: List<UserEntity>): Completable
}