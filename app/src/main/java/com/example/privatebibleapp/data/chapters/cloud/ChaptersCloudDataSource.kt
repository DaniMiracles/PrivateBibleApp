package com.example.privatebibleapp.data.chapters.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ChaptersCloudDataSource {

    suspend fun fetchChapters(bookId: Int): List<ChapterCloud>

    class Base(
        private val gson: Gson,
        private val chaptersService: ChaptersService
    ) : ChaptersCloudDataSource {

        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud> = gson.fromJson(
            chaptersService.fetchChapters(bookId).string(),
            object : TypeToken<List<ChapterCloud>>() {}.type
        )
    }
}