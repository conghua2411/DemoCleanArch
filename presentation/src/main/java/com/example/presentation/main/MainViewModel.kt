package com.example.presentation.main

import androidx.databinding.ObservableField
import com.example.domain.photo.PhotoInteractor
import com.example.domain.photo.PhotoModel
import com.example.domain.user.UserInteractor
import com.example.domain.user.UserModel
import com.example.presentation.base.BaseViewModel
import com.example.presentation.event.BaseEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val photoInteracter: PhotoInteractor,
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val textDemo = ObservableField("")
    val textDemoUser = ObservableField("")

    fun gotoDetail() {
        getEventViewModel().postValue(GotoDetailScreen())
    }

    fun insertData() {
        getCompositeDisposable().add(
            photoInteracter.savePhoto(generatePhoto())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemo.set("insert done")
                }, {
                    textDemo.set(it.localizedMessage)
                })
        )
    }

    fun getAllPhoto() {
        getCompositeDisposable().add(
            photoInteracter.getAllPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemo.set("data: \n$it")
                    getEventViewModel().postValue(ShowToast("get all photo"))
                }, {
                    textDemo.set(it.localizedMessage)
                })
        )
    }

    fun loadDataApi() {
        getCompositeDisposable().add(
            photoInteracter.loadDataApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemo.set("load data success, insert to db...")
                    saveDb(it)
                }, {
                    textDemo.set("load data failed : ${it.localizedMessage}")
                })
        )
    }

    private fun saveDb(it: List<PhotoModel>?) {
        getCompositeDisposable().add(
            photoInteracter.savePhoto(it as List<PhotoModel>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemo.set("${textDemo.get()} \ninsert data success")
                }, {
                    textDemo.set("insert data failed : ${it.localizedMessage}")
                })

        )
    }

    fun insertUserDb() {
        getCompositeDisposable().add(
            userInteractor.insertUsers(generateUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemoUser.set("insert success")
                    getEventViewModel().postValue(ShowToast("insert success"))
                }, {
                    textDemoUser.set("insert error: ${it.localizedMessage}")
                })
        )
    }

    fun getUsers() {
        getCompositeDisposable().add(
            userInteractor.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemoUser.set(it.toString())
                    getEventViewModel().postValue(ShowToast("get user success"))
                }, {
                    textDemoUser.set("get user error : ${it.localizedMessage}")
                })
        )
    }

    fun getUsersFlow() {
        getCompositeDisposable().add(
            userInteractor.getUsersFlow()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onError = {
                        getEventViewModel().postValue(ShowToast("get user flow error"))
                    },
                    onNext = {
                        getEventViewModel().postValue(ShowToast("get user : ${it.size}"))
                        textDemoUser.set(it.toString())
                    }
                )
        )
    }

    fun loadUserRemote() {
        getCompositeDisposable().add(
            userInteractor.loadUserRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemoUser.set("load user from api success, insert db...")
                    saveDbUser(it)
                }, {
                    textDemoUser.set("load user from api error : ${it.localizedMessage}")
                })
        )
    }

    fun deleteTbUser() {
        getCompositeDisposable().add(
                userInteractor.deleteAllUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy (
                        onError = {
                            getEventViewModel().postValue(ShowToast("delete all user error"))
                        },
                        onComplete = {
                            getEventViewModel().postValue(ShowToast("delete all user success"))
                        }
                    )
        )
    }

    private fun saveDbUser(list: List<UserModel>?) {
        getCompositeDisposable().add(
            userInteractor.insertUsers(list as List<UserModel>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    textDemoUser.set("${textDemoUser.get()} \ninsert user db success")
                }, {
                    textDemoUser.set("insert user db error : ${it.localizedMessage}")
                })
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

    private fun generatePhoto(): List<PhotoModel> {
        val list = mutableListOf<PhotoModel>()

        for (i in 1..5) {
            list.add(PhotoModel(null, (1..10).random().toLong(), "", "", ""))
        }
        return list
    }

    // main events
    inner class ShowDialogEvent(
        val title: String,
        val confirm: String
    ) : BaseEvent()

    inner class ShowToast(
        val text: String
    ) : BaseEvent()

    inner class GotoDetailScreen: BaseEvent()
}