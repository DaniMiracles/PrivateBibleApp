package com.example.privatebibleapp.data.chapters

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.privatebibleapp.data.chapters.cache.ChapterDb
import com.example.privatebibleapp.domain.chapters.ChapterDomain

data class ChapterData(private val chapterId: ChapterId) :
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun map(mapper: ChapterDataToDomainMapper): ChapterDomain =
        mapper.map(chapterId)

   fun mapToDb(mapper: ChapterDataToDbMapper, bookId: Int): ChapterDb =
       mapper.mapToDb(chapterId, bookId)


}