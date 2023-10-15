package com.example.privatebibleapp.domain.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.presenter.verses.VerseUi

interface VerseDomainToUiMapper : Abstract.Mapper {
    fun map(verseId: Int, text: String): VerseUi

    class Base() : VerseDomainToUiMapper {
        override fun map(verseId: Int, text: String): VerseUi = VerseUi.Base("$verseId. $text")
    }

}