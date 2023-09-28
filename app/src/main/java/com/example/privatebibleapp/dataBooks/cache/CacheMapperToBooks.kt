package com.example.privatebibleapp.dataBooks.cache

import com.example.privatebibleapp.dataBooks.BookData
import com.example.privatebibleapp.dataBooks.ToBookDataMapper

interface CacheMapperToBooks {

    fun map(listBookDb: List<BookDb>): List<BookData>

    class Base(private val toBookDataMapper: ToBookDataMapper) : CacheMapperToBooks {
        override fun map(listBookDb: List<BookDb>): List<BookData> =
            listBookDb.map { bookDb ->
                bookDb.map(toBookDataMapper)
            }
    }
}