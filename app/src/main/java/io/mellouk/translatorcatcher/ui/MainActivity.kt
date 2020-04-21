package io.mellouk.translatorcatcher.ui

import android.widget.Toast
import io.mellouk.translatorcatcher.R
import io.mellouk.translatorcatcher.base.BaseActivity
import io.mellouk.translatorcatcher.ui.Command.GetWords
import io.mellouk.translatorcatcher.ui.ViewState.*
import io.mellouk.translatorcatcher.utils.exhaustive
import io.mellouk.translatorcatcher.utils.hide
import io.mellouk.translatorcatcher.utils.show
import kotlinx.android.synthetic.main.activity_main.*

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
            is RoundIsReady -> renderRoundView(state)
            is Pending -> renderDefaultViewState()
            is Loading -> renderProgress()
            is WrongAnswer -> renderWrongAnswerView()
            is CorrectAnswer -> renderCorrectAnswerView(state)
            is Error -> renderErrorView(state)
        }.exhaustive
    }

    private fun prepareView() {
        roundView.hide()
        tvError.hide()
        viewModel.onCommand(GetWords)
    }

    private fun renderProgress() {
        roundView.hide()
        tvError.hide()
        progress.show()
    }

    private fun renderRoundView(state: RoundIsReady) {
        progress.hide()
        tvError.hide()

        roundView.show()
        with(state) {
            tvStatic.text = round.staticWord.english
            tvFalling.text = round.fallingWord.spanish
            tvScore.text = getString(R.string.score, round.score);
        }
    }

    private fun renderCorrectAnswerView(state: CorrectAnswer) {
        tvScore.text = getString(R.string.score, state.score)
        showToast(getText(R.string.correct_answer))
    }

    private fun renderWrongAnswerView() {
        showToast(getText(R.string.wrong_answer))
    }

    private fun renderErrorView(state: Error) {
        roundView.hide()
        progress.hide()

        tvError.text = state.message
        tvError.show()
    }

    private fun showToast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
