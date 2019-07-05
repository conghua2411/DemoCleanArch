package com.example.democleanarch.di

import android.app.Application
import com.example.democleanarch.DemoCleanArchApplication
import com.example.democleanarch.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        CacheModule::class,
        UIModule::class,
        PresentationModule::class,
        DomainModule::class,
        RemoteModule::class
    ]
)
interface AppComponent {

    fun inject(application: DemoCleanArchApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}