package com.example.privatebibleapp.data.verses.cloud

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.verses.ToVerseDataMapper
import com.google.gson.annotations.SerializedName

data class VerseCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("verseId")
    private val verseId: Int,
    @SerializedName("verse")
    private val text: String
) : Abstract.Object<VerseData, ToVerseDataMapper> {
    override fun map(mapper: ToVerseDataMapper): VerseData = mapper.map(id, verseId, text)
}