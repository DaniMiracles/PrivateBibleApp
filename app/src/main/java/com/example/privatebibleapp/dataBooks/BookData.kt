package com.example.privatebibleapp.dataBooks

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.dataBooks.cache.BookDataToDbMapper
import com.example.privatebibleapp.dataBooks.cache.BookDb
import com.example.privatebibleapp.domainBooks.BookDataToDomainMapper
import com.example.privatebibleapp.domainBooks.BookDomain

class BookData(private val id: Int, private val name: String) :
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    Abstract.Database<BookDb, BookDataToDbMapper> {

    override fun map(mapper: BookDataToDomainMapper): BookDomain = mapper.map(id, name)
    override fun mapToDb(mapper: BookDataToDbMapper): BookDb =
       mapper.mapToDb(id, name)

}