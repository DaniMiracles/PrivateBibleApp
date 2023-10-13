package com.example.privatebibleapp.data.chapters

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.Read
import com.example.privatebibleapp.data.ChapterId

interface ToChapterDataMapper : Abstract.Mapper.Data<Int, ChapterData> {

    abstract class Base(private val bookCache: Read<Pair<Int, String>>) : ToChapterDataMapper {
        override fun map(chapterId: Int): ChapterData {
            val realId = realId()
            return ChapterData(
                ChapterId.Base(
                    bookCache.read().first,
                    if (realId) chapterId else 0,
                    if (realId) 0 else chapterId
                )
            )
        }

        protected abstract fun realId(): Boolean
    }

    class Cloud(bookCache: Read<Pair<Int, String>>) : ToChapterDataMapper.Base(bookCache) {
        override fun realId(): Boolean = true
    }

    class Cache(bookCache: Read<Pair<Int, String>>) : ToChapterDataMapper.Base(bookCache) {
        override fun realId(): Boolean = false
    }

}