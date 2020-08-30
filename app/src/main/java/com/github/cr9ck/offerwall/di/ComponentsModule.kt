package com.github.cr9ck.offerwall.di

import com.github.cr9ck.offerwall.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComponentsModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}