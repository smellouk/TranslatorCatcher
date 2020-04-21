package io.mellouk.translatorcatcher.domain.usecase.getwords

import io.mellouk.translatorcatcher.domain.api.WordDto
import io.mellouk.translatorcatcher.domain.model.Word
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordMapper @Inject constructor() {
    fun map(dtos: List<WordDto>) = dtos.map { dto ->
        map(dto)
    }

    fun map(dto: WordDto) = Word(
        id = UUID.randomUUID().toString(),
        spanish = dto.textSpa ?: "",
        english = dto.textEng ?: ""
    )
}