package com.github.cr9ck.offerwall.di

import android.content.Context
import com.github.cr9ck.offerwall.Application
import com.github.cr9ck.offerwall.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        ModelModule::class,
        ViewModelModule::class,
        ComponentsModule::class]
)
@Singleton
interface MainComponent : AndroidInjector<Application> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MainComponent
    }
}