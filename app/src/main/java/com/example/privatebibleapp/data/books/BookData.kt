package com.example.privatebibleapp.data.books

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.books.cache.BookDataToDbMapper
import com.example.privatebibleapp.data.books.cache.BookDb
import com.example.privatebibleapp.domain.books.BookDomain

class BookData(private val id: Int, private val name: String,private val testament : String) :
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    Abstract.Database<BookDb, BookDataToDbMapper> {

    override fun map(mapper: BookDataToDomainMapper): BookDomain = mapper.map(id, name,testament)
    override fun mapToDb(mapper: BookDataToDbMapper): BookDb =
       mapper.mapToDb(id, name,testament)


    fun compare(otherTestament: String) = testament == otherTestament
    fun saveTestament() = testament

}