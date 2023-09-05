package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.dataBooks.cache.BooksCacheDataSource
import com.example.privatebibleapp.dataBooks.cloud.BooksCloudDataSource

interface BookRepository {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val booksCloudDataSource: BooksCloudDataSource,
        private val toBooksDataMapper: ToBooksDataMapper,
        private val booksCacheDataSource: BooksCacheDataSource,

        ) : BookRepository {

        override suspend fun fetchBooks(): BooksData = try {

            val listBookCloud = booksCloudDataSource.fetchBooks()
            val books = toBooksDataMapper.map(listBookCloud)
            BooksData.Success(books)
        } catch (e: Exception) {
            BooksData.Fail(e)
        }
    }
}