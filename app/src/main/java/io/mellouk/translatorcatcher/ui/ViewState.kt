package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.base.BaseViewState
import io.mellouk.translatorcatcher.domain.model.Round

sealed class ViewState : BaseViewState {
    object Initial : ViewState()
    class RoundIsReady(round: Round) : ViewState()
    object Pending : ViewState()
    object Loading : ViewState()
    class Error(message: String?) : ViewState()
}