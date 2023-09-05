package com.example.privatebibleapp.dataBooks.cloud

interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val booksServices: BooksServices) : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud> = booksServices.fetchBooks()

    }
}