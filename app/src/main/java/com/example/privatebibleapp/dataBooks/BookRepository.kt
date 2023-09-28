package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.dataBooks.cache.BooksCacheDataSource
import com.example.privatebibleapp.dataBooks.cache.CacheMapperToBooks
import com.example.privatebibleapp.dataBooks.cloud.BooksCloudDataSource

interface BookRepository : Repository<BookRepository> {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val booksCloudDataSource: BooksCloudDataSource,
        private val cloudMapperToBooks: CloudMapperToBooks,
        private val booksCacheDataSource: BooksCacheDataSource,
        private val cacheMapperToBooks: CacheMapperToBooks
    ) : BookRepository {

        override suspend fun fetchBooks(): BooksData = try {

            val listBookDb = booksCacheDataSource.read()
            if (listBookDb.isEmpty()) {
                val listBookCloud = booksCloudDataSource.fetchBooks()
                val listBookData = cloudMapperToBooks.map(listBookCloud)
                booksCacheDataSource.save(listBookData)
                BooksData.Success(listBookData)
            } else {
                BooksData.Success(cacheMapperToBooks.map(listBookDb))
            }
        } catch (e: Exception) {
            BooksData.Fail(e)
        }

    }
}