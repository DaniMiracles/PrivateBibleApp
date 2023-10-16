package com.example.privatebibleapp.data.chapters.cache

import com.example.privatebibleapp.data.chapters.ChapterData
import com.example.privatebibleapp.data.chapters.ToChapterDataMapper

interface CacheMapperToChapters {

    fun map(listChapterDb: List<ChapterDb>): List<ChapterData>

    class Base(private val toChapterDataMapper: ToChapterDataMapper) : CacheMapperToChapters {
        override fun map(listChapterDb: List<ChapterDb>): List<ChapterData> =
            listChapterDb.map { chapterDb -> chapterDb.map(toChapterDataMapper) }
    }
}