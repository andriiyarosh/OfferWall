package com.github.cr9ck.offerwall.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.github.cr9ck.offerwall.R
import com.github.cr9ck.offerwall.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
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
        nextButton.setOnClickListener { viewModel.nextRecord() }
    }

    @Inject
    fun setFactory(factory: ViewModelProvider.Factory) {
        this.factory = factory
    }

    private fun initObservers() {
        viewModel.viewState.observe(this, Observer {
            when(it) {
                is MainViewModel.DataLoadingState.DataLoaded -> {
                    handleViewState(it.viewState)
                    progress.isVisible = false
                    findViewById<View>(R.id.hostFragment).isVisible = true
                }
                MainViewModel.DataLoadingState.DataLoading -> {
                    progress.isVisible = true
                    findViewById<View>(R.id.hostFragment).isVisible = false
                }
                MainViewModel.DataLoadingState.Error -> {
                    progress.isVisible = false
                    findViewById<View>(R.id.hostFragment).isVisible = false
                }
            }
        })
    }

    private fun handleViewState(viewState: MainViewModel.ViewState) {
        when(viewState) {
            is MainViewModel.ViewState.TextState -> {
                findNavController(R.id.hostFragment).navigate(TextViewFragmentDirections.actionToText(viewState.text))
            }
            is MainViewModel.ViewState.WebState -> {
                findNavController(R.id.hostFragment).navigate(WebViewFragmentDirections.actionToWeb(viewState.url))
            }
            MainViewModel.ViewState.Game -> {

            }
        }
    }
}