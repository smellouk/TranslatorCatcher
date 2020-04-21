package io.mellouk.translatorcatcher.di.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mellouk.translatorcatcher.domain.api.WordDto
import io.mellouk.translatorcatcher.domain.api.WordsService
import io.reactivex.Single
import java.io.IOException
import kotlin.text.Charsets.UTF_8

class WordsServiceImpl(
    private val context: Context,
    private val gson: Gson
) : WordsService {
    override fun getWords(): Single<List<WordDto>> = Single.just(
        loadJsonFromAsset()?.let { json ->
            val turnsType = object : TypeToken<List<WordDto>>() {}.type
            gson.fromJson<List<WordDto>>(json, turnsType)
        } ?: emptyList()
    )

    private fun loadJsonFromAsset(): String? = try {
        context.assets.open("words_v2.json").run {
            val buffer = ByteArray(available())
            read(buffer)
            close()
            String(buffer, UTF_8)
        }
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }

}