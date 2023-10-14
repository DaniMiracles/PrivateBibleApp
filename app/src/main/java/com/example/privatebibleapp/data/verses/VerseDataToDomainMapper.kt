package com.example.privatebibleapp.data.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domain.books.BookDomain
import com.example.privatebibleapp.domain.verses.VerseDomain

interface VerseDataToDomainMapper : Abstract.Mapper {

    fun map(verseId: Int, text: String): VerseDomain

    class Base() : VerseDataToDomainMapper {
        override fun map(verseId: Int, text: String): VerseDomain = VerseDomain(verseId, text)

    }
}