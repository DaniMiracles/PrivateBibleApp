package com.example.privatebibleapp.data.books.cache

import com.example.privatebibleapp.data.books.BookData
import com.example.privatebibleapp.data.books.ToBookDataMapper

interface CacheMapperToBooks {

    fun map(listBookDb: List<BookDb>): List<BookData>

    class Base(private val toBookDataMapper: ToBookDataMapper) : CacheMapperToBooks {
        override fun map(listBookDb: List<BookDb>): List<BookData> =
            listBookDb.map { bookDb ->
                bookDb.map(toBookDataMapper)
            }
    }
}