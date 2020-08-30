package com.github.cr9ck.offerwall

import com.github.cr9ck.offerwall.di.DaggerMainComponent
import com.github.cr9ck.offerwall.di.MainComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class Application: DaggerApplication() {

    private lateinit var mainComponent: MainComponent

    override fun onCreate() {
        mainComponent = DaggerMainComponent.factory().create(this)
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return mainComponent
    }
}