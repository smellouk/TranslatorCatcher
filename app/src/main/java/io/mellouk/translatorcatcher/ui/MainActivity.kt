package io.mellouk.translatorcatcher.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import io.mellouk.translatorcatcher.BuildConfig
import io.mellouk.translatorcatcher.R
import io.mellouk.translatorcatcher.base.BaseActivity
import io.mellouk.translatorcatcher.di.AppModule
import io.mellouk.translatorcatcher.di.DaggerAppComponent
import io.mellouk.translatorcatcher.di.api.ClientModule
import io.mellouk.translatorcatcher.domain.usecase.matchwords.MatchWordsParams
import io.mellouk.translatorcatcher.ui.Command.*
import io.mellouk.translatorcatcher.ui.ViewState.*
import io.mellouk.translatorcatcher.utils.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ViewState, MainViewModel>(
    R.layout.activity_main
) {
    private var animator: ObjectAnimator? = null
    private val animationEndListener = object : AnimationEndListener() {
        override fun onAnimationEnd(animation: Animator?) {
            onNextRound()
        }
    }

    override fun getViewModelClass() = MainViewModel::class.java

    override fun inject() {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .clientModule(
                ClientModule(
                    BuildConfig.BASE_URL,
                    BuildConfig.DEBUG
                )
            )
            .build().apply {
                inject(this@MainActivity)
            }
    }

    override fun renderViewState(state: ViewState) {
        when (state) {
            is Initial -> prepareView()
            is RoundIsReady -> renderRoundView(state)
            is Pending -> renderDefaultViewState()
            is Loading -> renderProgress()
            is WrongAnswer -> {
                renderWrongAnswerView()
                onNextRound()
            }
            is CorrectAnswer -> {
                renderCorrectAnswerView()
                onNextRound()
            }
            is Error -> renderErrorView(state)
        }.exhaustive
    }

    override fun onDestroy() {
        super.onDestroy()
        animator?.clear()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnCorrect -> {
                btnCorrect.disable()
                onMatchWords(true)
            }

            R.id.btnWrong -> {
                btnCorrect.disable()
                onMatchWords(false)
            }
        }
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
        btnCorrect.enable()
        btnWrong.enable()

        roundView.show()
        with(state) {
            tvStatic.text = round.staticWord.english
            tvStatic.tag = round.staticWord.id
            tvFalling.text = round.fallingWord.spanish
            tvFalling.tag = round.fallingWord.id
            tvFalling.startFallingAnimation()
            tvScore.text = getString(R.string.score, round.score)
        }
    }

    private fun renderCorrectAnswerView() {
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.CENTER, 0, 0)
        }.show()
    }

    private fun onNextRound() {
        animator?.clear()
        viewModel.onCommand(NextRound)
    }

    private fun onMatchWords(hasSelectedCorrect: Boolean) {
        viewModel.onCommand(
            MatchWords(
                MatchWordsParams(
                    tvStatic.tag as String,
                    tvFalling.tag as String,
                    hasSelectedCorrect
                )
            )
        )
    }

    private fun ObjectAnimator?.clear() {
        this?.removeAllListeners()
        this?.cancel()
    }

    private fun TextView.startFallingAnimation() {
        animator = ObjectAnimator.ofFloat(
            this,
            "translationY",
            0F,
            root.height.toFloat()
        ).apply {
            duration = 2500
            interpolator = DecelerateInterpolator()
            addListener(animationEndListener)
            start()
        }
    }
}
