package io.mellouk.translatorcatcher.domain

import io.reactivex.Single

interface ParamBaseUseCase<Params : BaseParams, DataState : BaseDataState> {
    fun buildObservable(params: Params): Single<DataState>
}