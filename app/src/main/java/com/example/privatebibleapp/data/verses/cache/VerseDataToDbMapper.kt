package com.example.privatebibleapp.data.verses.cache

import com.example.privatebibleapp.core.Abstract

interface VerseDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, verseId: Int, text: String, chapterId: Int): VerseDb

    class Base() : VerseDataToDbMapper {
        override fun mapToDb(id: Int, verseId: Int, text: String, chapterId: Int): VerseDb =
            VerseDb(id, verseId, text, chapterId)
    }
}