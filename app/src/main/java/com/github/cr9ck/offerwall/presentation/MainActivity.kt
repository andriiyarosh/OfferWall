package com.github.cr9ck.offerwall.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.github.cr9ck.offerwall.R
import com.github.cr9ck.offerwall.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var factory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            factory
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
    }

    @Inject
    fun setFactory(factory: ViewModelProvider.Factory) {
        this.factory = factory
    }

    private fun initObservers() {
        viewModel.viewState.observe(this, Observer {
            when(it) {
                is MainViewModel.ViewState.TextState -> {
                    findNavController(R.id.hostFragment).navigate(TextViewFragmentDirections.actionToText(it.text))
                }
                is MainViewModel.ViewState.WebState -> {
                    findNavController(R.id.hostFragment).navigate(WebViewFragmentDirections.actionToText(it.url))
                }
                MainViewModel.ViewState.Game -> {

                }
                MainViewModel.ViewState.Error -> {

                }
            }
        })
    }
}