package com.example.privatebibleapp.data.chapters.cloud

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.chapters.ChapterData
import com.example.privatebibleapp.data.chapters.ToChapterDataMapper
import com.google.gson.annotations.SerializedName

data class ChapterCloud(
    @SerializedName("id")
    private val id : Int
) : Abstract.Object<ChapterData, ToChapterDataMapper> {
    override fun map(mapper: ToChapterDataMapper): ChapterData =
        mapper.map(id)

}