package com.example.cache.db.user

import androidx.room.*
import com.example.cache.Constants
import com.example.cache.db.base.BaseDAO
import io.reactivex.Flowable

@Dao
abstract class UserDAO : BaseDAO<User> {
    @Query("SELECT * FROM ${Constants.USER_TABLE_NAME}")
    abstract fun getUsers(): List<User>

    @Query("SELECT * FROM ${Constants.USER_TABLE_NAME}")
    abstract fun getUsersFlow(): Flowable<List<User>>

    @Query("SELECT * FROM ${Constants.USER_TABLE_NAME} WHERE id = :id")
    abstract fun getUserById(id: Long): User

    @Query("DELETE FROM ${Constants.USER_TABLE_NAME}")
    abstract fun deleteAllUsers()

    @Transaction
    open fun updateData(users: List<User>) {
        deleteAllUsers()
        updateData(users)
    }
}