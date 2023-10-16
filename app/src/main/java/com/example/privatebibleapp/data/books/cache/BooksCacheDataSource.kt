package com.example.privatebibleapp.data.books.cache

import com.example.privatebibleapp.data.books.BookData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface BooksCacheDataSource {
    suspend fun read(): List<BookDb>
    suspend fun save(booksData: List<BookData>)

    class Base(
        private val booksDao: BooksDao,
        private val bookDataToDbMapper: BookDataToDbMapper
    ) : BooksCacheDataSource {


        private val mutex = Mutex()
        override suspend fun read(): List<BookDb> = mutex.withLock { booksDao.fetchAllBooks() }


        override suspend fun save(booksData: List<BookData>) = mutex.withLock {
            val listBookDb = booksData.map { bookData -> bookData.mapToDb(bookDataToDbMapper) }
            booksDao.insertAllBooks(listBookDb)
        }
    }

}
