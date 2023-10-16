package com.example.privatebibleapp.presenter.verses

import com.example.privatebibleapp.core.ComparableTextMapper
import com.example.privatebibleapp.core.TextMapper

sealed class VerseUi : ComparableTextMapper<VerseUi> {

    class Base(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    class Fail(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    object Progress : VerseUi() {
        override fun map(mapper: TextMapper) = Unit
    }
}