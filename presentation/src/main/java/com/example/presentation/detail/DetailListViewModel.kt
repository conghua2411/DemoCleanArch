package com.example.presentation.detail

import androidx.lifecycle.MutableLiveData
import com.example.domain.photo.PhotoInteractor
import com.example.domain.photo.PhotoModel
import com.example.domain.user.UserInteractor
import com.example.domain.user.UserModel
import com.example.presentation.base.BaseViewModel
import com.example.presentation.event.BaseEvent
import com.example.presentation.model.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailListViewModel @Inject constructor(
    private val photoInteracter: PhotoInteractor,
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val userData = MutableLiveData<PhotoModel>()

    fun getAllPhotoDb() {
        getCompositeDisposable().add(
            photoInteracter.getAllPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        getEventViewModel().postValue(ShowToast(it.localizedMessage))
                    },
                    onNext = {
                        // save data
                        getEventViewModel().postValue(ShowToast("get photo success : ${it.size}"))
                    }
                )
        )
    }

    fun getUsersFlow() {
        getCompositeDisposable().add(
            userInteractor.getUsersFlow()
                .map { list ->
                    list.map {
                        UserData.createUserModelFromInfo(
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
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onError = {
                        getEventViewModel().postValue(ShowToast("get user flow error"))
                    },
                    onNext = {
                        getEventViewModel().postValue(ShowToast("get user : ${it.size}"))
                        getEventViewModel().postValue(UpdateListUser(it))
                    }
                )
        )
    }

    fun getAllUserDb() {
        getCompositeDisposable().add(
            userInteractor.getUsers()
                .map { list ->
                    list.map {
                        UserData.createUserModelFromInfo(
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
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        getEventViewModel().postValue(ShowToast(it.localizedMessage))
                    },
                    onNext = {
                        getEventViewModel().postValue(ShowToast("get user success : ${it.size}"))
                        getEventViewModel().postValue(UpdateListUser(it))
                    }
                )
        )
    }

    fun insertUser() {
        getCompositeDisposable().add(
            userInteractor.insertUsers(generateUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onError = {
                        getEventViewModel().postValue(ShowToast("insert user error"))
                    },
                    onComplete = {
                        getEventViewModel().postValue(ShowToast("insert user success"))
                    }
                )
        )
    }

    fun deleteUser(userData: UserData) {
        getCompositeDisposable().add(
            userInteractor.deleteUser(
                UserModel.createUserModelFromInfo(
                    userData.id,
                    userData.name,
                    userData.username,
                    userData.email,
                    userData.address?.street,
                    userData.address?.suite,
                    userData.address?.city,
                    userData.address?.zipcode,
                    userData.address?.geo?.lat,
                    userData.address?.geo?.lng,
                    userData.phone,
                    userData.website,
                    userData.company?.name,
                    userData.company?.catchPhrase,
                    userData.company?.bs
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onError = {
                        getEventViewModel().postValue(ShowToast("delete user error"))
                    },
                    onComplete = {
                        getEventViewModel().postValue(ShowToast("delete user success"))
                    }
                )
        )
    }

    private fun generateUser(): List<UserModel> {
        val list = mutableListOf<UserModel>()
        for (i in 1..2) {
            list.add(
                UserModel.createUserModelFromInfo(
                    null,
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString(),
                    i.toString()
                )
            )
        }
        return list
    }

    // detail events
    inner class ShowDialogEvent(
        val title: String,
        val confirm: String
    ) : BaseEvent()

    inner class ShowToast(
        val text: String
    ) : BaseEvent()

    inner class UpdateListUser(
        val list: List<UserData>
    ) : BaseEvent()
}