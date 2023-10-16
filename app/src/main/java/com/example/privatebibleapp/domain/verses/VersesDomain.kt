package com.example.privatebibleapp.domain.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ErrorType
import com.example.privatebibleapp.presenter.verses.VersesUi

sealed class VersesDomain : Abstract.Object<VersesUi, VersesDomainToUiMapper> {

    class Success(private val versesDomain: List<VerseDomain>) : VersesDomain() {
        override fun map(mapper: VersesDomainToUiMapper): VersesUi = mapper.map(versesDomain)
    }

    class Fail(private val errorType: ErrorType) : VersesDomain() {
        override fun map(mapper: VersesDomainToUiMapper): VersesUi = mapper.map(errorType)

    }
}