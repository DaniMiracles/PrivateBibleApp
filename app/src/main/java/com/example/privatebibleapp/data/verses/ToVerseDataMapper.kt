package com.example.privatebibleapp.data.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.verses.cloud.VerseData

interface ToVerseDataMapper : Abstract.Mapper {

    fun map(id: Int, verseId: Int, text: String): VerseData

    class Base() : ToVerseDataMapper {
        override fun map(id: Int, verseId: Int, text: String): VerseData =
            VerseData(id, verseId, text)
    }
}