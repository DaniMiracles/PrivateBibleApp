package com.example.privatebibleapp.domain.chapters

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.ChapterId
import com.example.privatebibleapp.data.ChapterIdToUiMapper
import com.example.privatebibleapp.presenter.chapters.ChapterUi

interface ChapterDomainToUiMapper : Abstract.Mapper {

    fun map(data: ChapterId): ChapterUi

    class Base(
        private val chapterIdToUiMapper: ChapterIdToUiMapper
    ) : ChapterDomainToUiMapper {
        override fun map(data: ChapterId): ChapterUi =
            data.map(chapterIdToUiMapper)
    }
}