package com.example.privatebibleapp.data.verses


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
        private val cacheMapperToVerses: CacheMapperToVerses
    ) : VersesRepository {

        override suspend fun fetchVerses(): VersesData {}

    }
}