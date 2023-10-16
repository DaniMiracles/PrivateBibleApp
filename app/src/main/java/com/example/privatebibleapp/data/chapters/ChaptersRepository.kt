package com.example.privatebibleapp.data.chapters


import com.example.privatebibleapp.core.Read
import com.example.privatebibleapp.data.chapters.cache.CacheMapperToChapters
import com.example.privatebibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.privatebibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.privatebibleapp.data.chapters.cloud.CloudMapperToChapters

interface ChaptersRepository {

    suspend fun fetchChapters(): ChaptersData

    class Base(
        private val chaptersCloudDataSource: ChaptersCloudDataSource,
        private val cloudMapperToChapters: CloudMapperToChapters,
        private val chapterCacheDataSource: ChaptersCacheDataSource,
        private val cacheMapperToChapters: CacheMapperToChapters,
        private val bookIdContainer: Read<Pair<Int, String>>
    ) : ChaptersRepository {


        override suspend fun fetchChapters(): ChaptersData = try {
            val bookId = bookIdContainer.read().first
            val listChapterDb = chapterCacheDataSource.read(bookId)
            if (listChapterDb.isEmpty()) {
                val listChapterCloud = chaptersCloudDataSource.fetchChapters(bookId)
                val listChapterData = cloudMapperToChapters.map(listChapterCloud)
                chapterCacheDataSource.saveChapters(bookId, listChapterData)
                ChaptersData.Success(listChapterData)
            } else {
                ChaptersData.Success(cacheMapperToChapters.map(listChapterDb))
            }
        } catch (e: Exception) {
            ChaptersData.Fail(e)
        }
    }
}