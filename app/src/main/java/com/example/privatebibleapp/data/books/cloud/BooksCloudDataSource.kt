package com.example.privatebibleapp.data.books.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(
        private val booksServices: BooksServices,
        private val gson: Gson
    ) : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud> =
            gson.fromJson(
                booksServices.fetchBooks().string(),
                object : TypeToken<List<BookCloud>>() {}.type
            )
    }
}