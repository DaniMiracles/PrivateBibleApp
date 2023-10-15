package com.example.privatebibleapp.data.verses.cache

import com.example.privatebibleapp.data.verses.cloud.VerseData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface VersesCacheDataSource {

    suspend fun read(chapterId: Int): List<VerseDb>
    suspend fun save(verseData: List<VerseData>, chapterId: Int)

    class Base(
        private val versesDao: VersesDao,
        private val verseDataToDbMapper: VerseDataToDbMapper
    ) : VersesCacheDataSource {

        private val mutex = Mutex()
        override suspend fun read(chapterId: Int): List<VerseDb> = mutex.withLock {
            versesDao.getVersesByChapterId(chapterId)
        }

        override suspend fun save(verseData: List<VerseData>, chapterId: Int) = mutex.withLock {
            val listVerseDb =
                verseData.map { verseData -> verseData.mapToDb(verseDataToDbMapper, chapterId) }
            versesDao.insertVerses(listVerseDb)
        }

    }

}