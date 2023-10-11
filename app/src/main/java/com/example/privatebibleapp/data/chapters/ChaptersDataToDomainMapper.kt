package com.example.privatebibleapp.data.chapters

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domain.chapters.ChaptersDomain

abstract class ChaptersDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<ChapterData>, ChaptersDomain>() {

    class Base(
        private val chapterDataToDomainMapper: ChapterDataToDomainMapper
    ) : ChaptersDataToDomainMapper() {

        override fun map(data: List<ChapterData>): ChaptersDomain =
            ChaptersDomain.Success(data.map { chapterData ->
                chapterData.map(chapterDataToDomainMapper)
            })

        override fun map(e: Exception): ChaptersDomain =
            ChaptersDomain.Fail(errorType(e))
    }
}