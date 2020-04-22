package io.mellouk.translatorcatcher.ui

import io.mellouk.translatorcatcher.base.BaseCommand
import io.mellouk.translatorcatcher.domain.usecase.matchwords.MatchWordsParams

sealed class Command : BaseCommand {
    object GetWords : Command()
    object NextRound : Command()
    class MatchWords(val params: MatchWordsParams) : Command()
}