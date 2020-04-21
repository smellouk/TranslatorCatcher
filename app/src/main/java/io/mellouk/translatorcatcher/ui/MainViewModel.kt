package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.base.BaseViewModel
import io.mellouk.translatorcatcher.domain.usecase.getwords.GetWordsDataState
import io.mellouk.translatorcatcher.domain.usecase.getwords.GetWordsUseCase
import io.mellouk.translatorcatcher.domain.usecase.nextround.PrepareNextRoundUseCase
import io.mellouk.translatorcatcher.ui.Command.*
import io.mellouk.translatorcatcher.ui.ViewState.Initial
import io.mellouk.translatorcatcher.ui.ViewState.Pending
import io.mellouk.translatorcatcher.utils.Commandable
import io.mellouk.translatorcatcher.utils.exhaustive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase,
    private val viewStateMapper: ViewStateMapper,
    private val prepareNextRoundUseCase: PrepareNextRoundUseCase
) : BaseViewModel<ViewState>(), Commandable<Command> {

    override fun getInitialState(): ViewState = Initial

    override fun onCommand(cmd: Command) {
        liveData.value = commandHandler(cmd)
    }

    private fun commandHandler(cmd: Command): ViewState = when (cmd) {
        is GetWords -> getWords()
        is NextRound -> prepareNextRound()
        is MatchWord -> TODO()
        is CalculateScore -> TODO()
    }.exhaustive

    private fun getWords(): Pending {
        addObservable(
            source = getWordsUseCase.buildObservable(),
            onNext = { dataState ->
                liveData.value = if (dataState == GetWordsDataState.Successful) {
                    prepareNextRound()
                } else {
                    viewStateMapper.map(dataState)
                }
            },
            onError = { throwable ->
                liveData.value = viewStateMapper.map(throwable)
            }
        )

        return Pending
    }

    private fun prepareNextRound(): Pending {
        addObservable(
            source = prepareNextRoundUseCase.buildObservable(),
            onNext = { dataState ->
                liveData.value = viewStateMapper.map(dataState)
            },
            onError = { throwable ->
                liveData.value = viewStateMapper.map(throwable)
            }
        )

        return Pending
    }
}