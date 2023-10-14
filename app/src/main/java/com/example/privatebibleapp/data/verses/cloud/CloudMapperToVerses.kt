package com.example.privatebibleapp.data.verses.cloud

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.verses.ToVerseDataMapper

interface CloudMapperToVerses : Abstract.Mapper {

    fun map(listVerseCloud: List<VerseCloud>): List<VerseData>

    class Base(private val toVerseDataMapper: ToVerseDataMapper) : CloudMapperToVerses {
        override fun map(listVerseCloud: List<VerseCloud>): List<VerseData> {
            val listVerseData = listVerseCloud.map { verseCloud ->
                verseCloud.map(toVerseDataMapper)
            }
            return listVerseData
        }

    }
}