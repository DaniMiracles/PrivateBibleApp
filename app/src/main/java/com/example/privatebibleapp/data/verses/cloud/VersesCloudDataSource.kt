package com.example.privatebibleapp.data.verses.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface VersesCloudDataSource {

    suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud>

    class Base(
        private val versesServices: VersesServices,
        private val gson: Gson
    ) : VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud> =
            gson.fromJson(
                versesServices.fetchVerses(bookId, chapterId).string(),
                object : TypeToken<List<VerseCloud>>() {}.type
            )

    }
}