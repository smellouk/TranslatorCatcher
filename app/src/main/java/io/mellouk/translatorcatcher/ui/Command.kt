package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.base.BaseCommand

sealed class Command : BaseCommand {
    object GetWords : Command()
    object NextRound : Command()
    object MatchWord : Command()
    object CalculateScore : Command()
}