package com.example.privatebibleapp.data.verses


import com.example.privatebibleapp.core.Read
import com.example.privatebibleapp.data.verses.cache.CacheMapperToVerses
import com.example.privatebibleapp.data.verses.cache.VersesCacheDataSource
import com.example.privatebibleapp.data.verses.cloud.CloudMapperToVerses
import com.example.privatebibleapp.data.verses.cloud.VersesCloudDataSource

interface VersesRepository {

    suspend fun fetchVerses(): VersesData

    class Base(
        private val versesCloudDataSource: VersesCloudDataSource,
        private val cloudMapperToVerses: CloudMapperToVerses,
        private val versesCacheDataSource: VersesCacheDataSource,
        private val cacheMapperToVerses: CacheMapperToVerses,
        private val chapterIdContainer: Read<Int>,
        private val bookIdContainer: Read<Pair<Int, String>>
    ) : VersesRepository {

        override suspend fun fetchVerses(): VersesData = try {
            val bookId = bookIdContainer.read().first
            val chapterId = chapterIdContainer.read()
            val generatedChapterId = 1000 * bookId + chapterId

            val versesCache = versesCacheDataSource.read(generatedChapterId)
            if (versesCache.isEmpty()) {
                val versesCloud = versesCloudDataSource.fetchVerses(bookId, chapterId)
                val versesData = cloudMapperToVerses.map(versesCloud)
                versesCacheDataSource.save(versesData, generatedChapterId)
                VersesData.Success(versesData)
            } else {
                VersesData.Success(cacheMapperToVerses.map(versesCache))
            }
        } catch (e: Exception) {
            VersesData.Fail(e)
        }
    }
}