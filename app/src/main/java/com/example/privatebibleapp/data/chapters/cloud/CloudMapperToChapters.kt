package com.example.privatebibleapp.data.chapters.cloud


import com.example.privatebibleapp.data.chapters.ChapterData
import com.example.privatebibleapp.data.chapters.ToChapterDataMapper

interface CloudMapperToChapters {

    fun map(listChapterCloud: List<ChapterCloud>): List<ChapterData>

    class Base(private val toChapterDataMapper: ToChapterDataMapper) : CloudMapperToChapters {

        override fun map(listChapterCloud: List<ChapterCloud>): List<ChapterData> =
            listChapterCloud.map { chapterCloud ->
                chapterCloud.map(toChapterDataMapper)
            }
    }
}