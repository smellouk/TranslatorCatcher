package io.mellouk.translatorcatcher.domain

import io.reactivex.Observable

interface ParamBaseUseCase<Params : BaseParams, DataState : BaseDataState> {
    fun buildObservable(params: Params): Observable<DataState>
}