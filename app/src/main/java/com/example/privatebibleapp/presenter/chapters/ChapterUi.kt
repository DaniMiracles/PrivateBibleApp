package com.example.privatebibleapp.presenter.chapters

import com.example.privatebibleapp.core.ComparableTextMapper
import com.example.privatebibleapp.core.TextMapper

sealed class ChapterUi : ComparableTextMapper<ChapterUi> {

    override fun map(mapper: TextMapper) = Unit
    open fun open(show: Show) = Unit

    class Base(private val id: Int, private val text: String) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
        override fun open(show: Show) = show.show(id)
    }

    class Fail(private val errorType: String) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(errorType)
    }

    object Progress : ChapterUi()
}