package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.domain.BaseDataState
import io.mellouk.translatorcatcher.domain.usecase.getwords.GetWordsDataState.Fail
import io.mellouk.translatorcatcher.domain.usecase.getwords.GetWordsDataState.Successful
import io.mellouk.translatorcatcher.ui.ViewState.*
import io.mellouk.translatorcatcher.utils.exhaustive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewStateMapper @Inject constructor() {
    fun map(dataState: BaseDataState): ViewState = when (dataState) {
        is Successful -> ViewIsReady
        is Fail -> map(dataState.throwable)
        else -> Pending
    }.exhaustive

    fun map(throwable: Throwable?): ViewState = Error(throwable?.message)
}