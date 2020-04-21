package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.R
import io.mellouk.translatorcatcher.base.BaseActivity
import io.mellouk.translatorcatcher.ui.Command.GetWords
import io.mellouk.translatorcatcher.ui.ViewState.*
import io.mellouk.translatorcatcher.utils.exhaustive

class MainActivity : BaseActivity<ViewState, MainViewModel>(
    R.layout.activity_main
) {
    override fun getViewModelClass() = MainViewModel::class.java

    override fun inject() {
        appComponent.inject(this)
    }

    override fun renderViewState(state: ViewState) {
        when (state) {
            is Initial -> prepareView()
            is RoundIsReady -> showView()
            is Pending -> renderDefaultViewState()
            is Loading -> renderDefaultViewState()
            is Error -> renderDefaultViewState()
        }.exhaustive
    }

    private fun prepareView() {
        viewModel.onCommand(GetWords)
    }

    private fun showView() {

    }
}
