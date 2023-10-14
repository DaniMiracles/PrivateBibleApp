package com.example.privatebibleapp.data.verses.cache

import com.example.privatebibleapp.data.verses.cloud.VerseData
import kotlinx.coroutines.sync.Mutex

interface VersesCacheDataSource {

    suspend fun read(): List<VerseDb>
    suspend fun save(verseData: List<VerseData>)

    class Base() :VersesCacheDataSource{

        private val mutex = Mutex()
        override suspend fun read(): List<VerseDb> {
            TODO("Not yet implemented")
        }

        override suspend fun save(verseData: List<VerseData>) {
            TODO("Not yet implemented")
        }

    }

}