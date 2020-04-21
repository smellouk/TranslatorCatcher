package io.mellouk.translatorcatcher.domain.api

import com.google.gson.annotations.SerializedName

class WordDto(

    @SerializedName("text_spa")
    val textSpa: String? = null,

    @SerializedName("text_eng")
    val textEng: String? = null
)