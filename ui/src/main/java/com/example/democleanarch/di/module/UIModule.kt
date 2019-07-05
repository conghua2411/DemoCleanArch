package com.example.democleanarch.di.module

import com.example.democleanarch.ui.MainActivity
import com.example.democleanarch.ui.listDetail.ListDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindListDetailActivity(): ListDetailActivity
}