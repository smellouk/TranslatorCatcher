package io.mellouk.translatorcatcher.domain

import io.reactivex.Observable

interface BaseUseCase<DataState : BaseDataState> {
    fun buildObservable(): Observable<DataState>
}