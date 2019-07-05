package com.example.remote.user

import com.example.data.repository.user.UserEntity
import com.example.data.repository.user.UserRemote
import com.example.remote.RetrofitService
import io.reactivex.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor(private val retrofitService: RetrofitService) : UserRemote {
    override fun getUsersRemote(): Observable<List<UserEntity>> =
        retrofitService.getUsers().map { t ->
            val list = mutableListOf<UserEntity>()
            t.forEach {
                list.add(
                    UserEntity.createUserEntityFromInfo(
                        it.id,
                        it.name,
                        it.username,
                        it.email,
                        it.address?.street,
                        it.address?.suite,
                        it.address?.city,
                        it.address?.zipcode,
                        it.address?.geo?.lat,
                        it.address?.geo?.lng,
                        it.phone,
                        it.website,
                        it.company?.name,
                        it.company?.catchPhrase,
                        it.company?.bs
                    )
                )
            }
            list
        }
}