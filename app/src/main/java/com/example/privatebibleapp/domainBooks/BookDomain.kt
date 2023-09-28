package com.example.privatebibleapp.domainBooks

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.presenterBooks.BookDomainToUiMapper
import com.example.privatebibleapp.presenterBooks.BookUi

class BookDomain(private val id: Int, private val name: String) :
    Abstract.Object<BookUi, BookDomainToUiMapper> {

    override fun map(mapper: BookDomainToUiMapper): BookUi =
        mapper.map(id, name)

}