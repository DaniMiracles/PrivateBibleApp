package com.example.privatebibleapp.presenter.chapters

import com.example.privatebibleapp.core.Abstract

sealed class ChaptersUi : Abstract.Object<Unit, ChaptersCommunication> {

    class Base(private val chaptersUi: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) =
            mapper.map(chaptersUi)
    }
}
