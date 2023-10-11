package com.example.privatebibleapp.data.chapters

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domain.chapters.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper> {

    class Success(private val listChapterData: List<ChapterData>) : ChaptersData() {

        override fun map(mapper: ChaptersDataToDomainMapper): ChaptersDomain =
            mapper.map(listChapterData)
    }

    class Fail(private val exception: Exception) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper): ChaptersDomain =
            mapper.map(exception)
    }
}