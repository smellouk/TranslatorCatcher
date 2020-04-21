package io.mellouk.translatorcatcher.domain.usecase.getwords

import io.mellouk.translatorcatcher.domain.BaseDataState

sealed class GetWordsDataState : BaseDataState {
    object Successful : GetWordsDataState()
    class Fail(val throwable: Throwable) : GetWordsDataState()
}