package io.mellouk.translatorcatcher.domain.repository

import io.mellouk.translatorcatcher.domain.api.WordsService
import io.mellouk.translatorcatcher.domain.model.Word

class WordsRepository(private val wordsService: WordsService) {
    private var memoryWords: List<Word>? = null

    fun getRemoteWords() = wordsService.getWords()

    fun getWords() = memoryWords ?: emptyList()

    fun saveWords(words: List<Word>) {
        memoryWords = words
    }
}