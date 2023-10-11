package com.example.privatebibleapp.data.chapters

import com.example.privatebibleapp.core.Abstract

interface ToChapterDataMapper : Abstract.Mapper {

    fun map(chapterId: Int): ChapterData

    class Base() : ToChapterDataMapper {
        override fun map(chapterId: Int): ChapterData = ChapterData(chapterId)
    }
}