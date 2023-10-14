package com.example.privatebibleapp.data.verses.cache

import com.example.privatebibleapp.data.verses.ToVerseDataMapper
import com.example.privatebibleapp.data.verses.cloud.VerseData

interface CacheMapperToVerses {

    fun map(listVerseDb: List<VerseDb>): List<VerseData>

    class Base(private val toVerseDataMapper: ToVerseDataMapper) : CacheMapperToVerses {
        override fun map(listVerseDb: List<VerseDb>): List<VerseData> = listVerseDb.map { verseDb ->
            verseDb.map(toVerseDataMapper)
        }
    }
}