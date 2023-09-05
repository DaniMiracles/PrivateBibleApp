package com.example.privatebibleapp.dataBooks.cache

interface BooksCacheDataSource {

    fun fetchBooks() : List<BookCache>

    fun mapToDb()

    class Base() : BooksCacheDataSource{
        override fun fetchBooks(): List<BookCache> {
            TODO("Not yet implemented")
        }

        override fun mapToDb() {
            TODO("Not yet implemented")
        }

    }
}