package io.mellouk.translatorcatcher.domain

import io.reactivex.Observable

interface BaseParamUseCase<Params : BaseParams, DataState : BaseDataState> {
    fun buildObservable(params: Params): Observable<DataState>
}