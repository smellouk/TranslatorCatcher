package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.base.BaseViewModel
import io.mellouk.translatorcatcher.ui.Command.*
import io.mellouk.translatorcatcher.ui.ViewState.Initial
import io.mellouk.translatorcatcher.ui.ViewState.Pending
import io.mellouk.translatorcatcher.utils.Commandable
import io.mellouk.translatorcatcher.utils.exhaustive
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor() : BaseViewModel<ViewState>(), Commandable<Command> {
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

        return Pending
    }

    private fun prepareNextRound(): Pending {

        return Pending
    }

}