package com.example.privatebibleapp.domain.chapters

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.core.ErrorType
import com.example.privatebibleapp.presenter.chapters.ChapterUi
import com.example.privatebibleapp.presenter.chapters.ChaptersUi

abstract class ChaptersDomainToUiMapper(manageResources: ManageResources) :
    Abstract.Mapper.DomainToUi.Base<List<ChapterDomain>, ChaptersUi>(manageResources) {

    class Base(
        manageResources: ManageResources,
        private val chapterDomainToUiMapper: ChapterDomainToUiMapper
    ) : ChaptersDomainToUiMapper(manageResources) {

        override fun map(data: List<ChapterDomain>): ChaptersUi {
            val chaptersUi = data.map { chapterDomain -> chapterDomain.map(chapterDomainToUiMapper)}
            return ChaptersUi.Base(chaptersUi)
        }
//            ChaptersUi.Base(data.map { chapterDomain ->
//                chapterDomain.map(chapterDomainToUiMapper) })


        override fun map(errorType: ErrorType): ChaptersUi =
            ChaptersUi.Base(listOf(ChapterUi.Fail(errorMessage(errorType))))
    }
}