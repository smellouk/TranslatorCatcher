package io.mellouk.translatorcatcher.domain.usecase.nextround

import io.mellouk.translatorcatcher.domain.BaseDataState
import io.mellouk.translatorcatcher.domain.model.Round

sealed class PrepareDataState : BaseDataState {
    class Successful(val round: Round) : PrepareDataState()
    class Fail(val throwable: Throwable) : PrepareDataState()
}