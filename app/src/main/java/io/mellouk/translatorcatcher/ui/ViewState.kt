package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.base.BaseViewState

sealed class ViewState : BaseViewState {
    object Initial : ViewState()
    object ViewIsReady : ViewState()
    object Pending : ViewState()
    class Error(message: String?) : ViewState()
}