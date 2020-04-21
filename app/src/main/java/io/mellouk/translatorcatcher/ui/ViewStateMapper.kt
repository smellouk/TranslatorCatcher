package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.domain.BaseDataState
import io.mellouk.translatorcatcher.domain.usecase.getwords.GetWordsDataState
import io.mellouk.translatorcatcher.domain.usecase.nextround.PrepareDataState
import io.mellouk.translatorcatcher.ui.ViewState.Error
import io.mellouk.translatorcatcher.ui.ViewState.Pending
import io.mellouk.translatorcatcher.utils.exhaustive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewStateMapper @Inject constructor() {
    fun map(dataState: BaseDataState): ViewState = when (dataState) {
        is GetWordsDataState.Fail -> map(dataState.throwable)
        is PrepareDataState.Successful -> ViewState.RoundIsReady(dataState.round)
        else -> Pending
    }.exhaustive

    fun map(throwable: Throwable?): ViewState = Error(throwable?.message)
}