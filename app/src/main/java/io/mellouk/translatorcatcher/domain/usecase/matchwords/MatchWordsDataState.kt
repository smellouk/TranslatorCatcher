package io.mellouk.translatorcatcher.domain.usecase.matchwords

import io.mellouk.translatorcatcher.domain.BaseDataState

sealed class MatchWordsDataState : BaseDataState {
    object Successful : MatchWordsDataState()
    object Fail : MatchWordsDataState()
}