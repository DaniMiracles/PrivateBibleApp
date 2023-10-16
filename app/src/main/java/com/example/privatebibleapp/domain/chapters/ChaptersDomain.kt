package com.example.privatebibleapp.domain.chapters


import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ErrorType
import com.example.privatebibleapp.presenter.chapters.ChaptersUi

sealed class ChaptersDomain : Abstract.Object<ChaptersUi, ChaptersDomainToUiMapper> {

    class Success(private val listChapterDomain: List<ChapterDomain>) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper): ChaptersUi =
            mapper.map(listChapterDomain)

    }

    class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper): ChaptersUi =
            mapper.map(errorType)
    }
}