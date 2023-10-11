package com.example.privatebibleapp.data.chapters.cache

import com.example.privatebibleapp.core.Abstract

interface ChapterDataToDbMapper : Abstract.Mapper {

    fun mapToDb(chapterId: Int,bookId : Int): ChapterDb

    class Base() : ChapterDataToDbMapper {
        override fun mapToDb(chapterId: Int, bookId: Int): ChapterDb = ChapterDb(chapterId, bookId)

    }
}