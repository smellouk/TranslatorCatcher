package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.base.BaseViewState
import io.mellouk.translatorcatcher.domain.model.Round

sealed class ViewState : BaseViewState {
    object Initial : ViewState()
    class RoundIsReady(val round: Round) : ViewState()
    object Pending : ViewState()
    object Loading : ViewState()
    object WrongAnswer : ViewState()
    class CorrectAnswer(val score: Int) : ViewState()
    class Error(val message: String?) : ViewState()
}