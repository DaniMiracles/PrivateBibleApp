package com.example.privatebibleapp.data.verses.cloud

import com.example.privatebibleapp.data.books.cloud.BookCloud
import com.example.privatebibleapp.data.books.cloud.BooksServices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface VersesCloudDataSource {

    suspend fun fetchVerses(): List<VerseCloud>

    class Base(
        private val versesServices: VersesServices,
        private val gson: Gson
    ) : VersesCloudDataSource {
        override suspend fun fetchVerses(): List<VerseCloud> =
            gson.fromJson(
                versesServices.fetchVerses().string(),
                object : TypeToken<List<VerseCloud>>() {}.type
            )

    }
}