package com.example.privatebibleapp.domain.books

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.presenter.books.BookUi


sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper> {
    class Base(private val id: Int, private val name: String) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi =
            mapper.map(id, name)
    }

    class Testament(private val type: TypeTestament) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi = mapper.map(type.getId(), type.name)

    }
}


enum class TypeTestament(private val id: Int) {
    NEW(Int.MIN_VALUE),
    OLD(Int.MAX_VALUE);

    fun getId() = id
}