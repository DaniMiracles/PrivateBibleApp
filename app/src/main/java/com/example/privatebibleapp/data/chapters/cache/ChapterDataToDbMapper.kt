package com.example.privatebibleapp.data.chapters.cache

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.ChapterId

interface ChapterDataToDbMapper : Abstract.Mapper {

    fun mapToDb(chapterId: ChapterId,bookId: Int): ChapterDb

    class Base() : ChapterDataToDbMapper {
        override fun mapToDb(chapterId: ChapterId,bookId : Int): ChapterDb = chapterId.mapToDb(bookId)

    }
}