package com.example.domain.user

import com.example.data.repository.user.UserDbRepo
import com.example.data.repository.user.UserEntity
import com.example.data.repository.user.UserRemote
import com.example.domain.mapper.Mapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userDbRepo: UserDbRepo,
    private val userRemote: UserRemote
) : Mapper<UserEntity, UserModel> {
    fun getUsers(): Observable<List<UserModel>> =
        userDbRepo.getUsers().map {
            mapEntityToModel(it)
        }

    fun getUsersFlow(): Flowable<List<UserModel>> =
        userDbRepo.getUsersFlow().map {
            mapEntityToModel(it)
        }

    fun getUserById(id: Long): Observable<UserModel> =
        userDbRepo.getUserById(id).map {
            mapEntityToModel(it)
        }

    fun insertUsers(list: List<UserModel>): Completable =
        userDbRepo.saveUsers(mapModelToEntity(list))

    fun updateUser(userModel: UserModel): Completable =
        userDbRepo.updateUser(mapModelToEntity(userModel))

    fun deleteUser(userModel: UserModel): Completable =
        userDbRepo.deleteUser(mapModelToEntity(userModel))

    fun deleteAllUser(): Completable =
        userDbRepo.deleteAllUsers()

    fun loadUserRemote(): Observable<List<UserModel>> =
        userRemote.getUsersRemote().map {
            mapEntityToModel(it)
        }

    override fun mapEntityToModel(e: UserEntity): UserModel =
        UserModel.createUserModelFromInfo(
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

    override fun mapEntityToModel(e: List<UserEntity>): List<UserModel> = e.map {
        mapEntityToModel(it)
    }

    override fun mapModelToEntity(m: UserModel): UserEntity =
        UserEntity.createUserEntityFromInfo(
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
            m.company?.name,
            m.company?.catchPhrase,
            m.company?.bs
        )

    override fun mapModelToEntity(m: List<UserModel>): List<UserEntity> = m.map {
        mapModelToEntity(it)
    }
}