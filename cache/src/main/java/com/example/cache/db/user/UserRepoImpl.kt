package com.example.cache.db.user

import android.util.Log
import com.example.cache.EntityModelMapper
import com.example.data.repository.user.UserDbRepo
import com.example.data.repository.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDAO: UserDAO) : UserDbRepo, EntityModelMapper<UserEntity, User> {

    override fun getUsers(): Observable<List<UserEntity>> = Observable.fromCallable {
        userDAO.getUsers()
    }.map {
        mapModelToEntity(it)
    }

    override fun getUsersFlow(): Flowable<List<UserEntity>> = userDAO.getUsersFlow()
        .distinctUntilChanged()
        .map {
            mapModelToEntity(it)
        }

    override fun getUserById(id: Long): Observable<UserEntity> = Observable.fromCallable {
        userDAO.getUserById(id)
    }.map {
        mapModelToEntity(it)
    }

    override fun saveUsers(list: List<UserEntity>): Completable = Completable.defer {
        list.forEach {
            Log.d("saveUser", "user : $it")
            userDAO.insert(mapEntityToModel(it))
        }
        Completable.complete()
    }

    override fun updateUser(userEntity: UserEntity): Completable = Completable.defer {
        userDAO.update(mapEntityToModel(userEntity))
        Completable.complete()
    }

    override fun deleteUser(userEntity: UserEntity): Completable = Completable.defer {
        userDAO.delete(mapEntityToModel(userEntity))
        Completable.complete()
    }

    override fun deleteAllUsers(): Completable = Completable.defer {
        userDAO.deleteAllUsers()
        Completable.complete()
    }

    override fun updateDataUser(users: List<UserEntity>): Completable = Completable.defer {
        userDAO.updateData(mapEntityToModel(users))
        Completable.complete()
    }

    override fun mapEntityToModel(e: UserEntity): User = User.createUserFromInfo(
        e.id,
        e.name,
        e.username,
        e.email,
        e.address?.street,
        e.address?.suite,
        e.address?.city,
        e.address?.zipcode,
        e.address?.geo?.lat,
        e.address?.geo?.lng,
        e.phone,
        e.website,
        e.company?.name,
        e.company?.catchPhrase,
        e.company?.bs
    )

    override fun mapEntityToModel(e: List<UserEntity>): List<User> = e.map {
        mapEntityToModel(it)
    }

    override fun mapModelToEntity(m: User): UserEntity = UserEntity.createUserEntityFromInfo(
        m.id,
        m.name,
        m.username,
        m.email,
        m.address?.street,
        m.address?.suite,
        m.address?.city,
        m.address?.zipcode,
        m.address?.geo?.lat,
        m.address?.geo?.lng,
        m.phone,
        m.website,
        m.company?.cname,
        m.company?.catchPhrase,
        m.company?.bs
    )

    override fun mapModelToEntity(m: List<User>): List<UserEntity> = m.map {
        mapModelToEntity(it)
    }
}