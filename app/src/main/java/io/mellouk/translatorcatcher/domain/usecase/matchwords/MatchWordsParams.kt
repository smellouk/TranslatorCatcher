package io.mellouk.translatorcatcher.domain.usecase.matchwords

import io.mellouk.translatorcatcher.domain.BaseParams

class MatchWordsParams(
    val staticWordId: String,
    val fallingWordId: String,
    val hasSelectedCorrect: Boolean
) : BaseParams