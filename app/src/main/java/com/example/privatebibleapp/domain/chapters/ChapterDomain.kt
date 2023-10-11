package com.example.privatebibleapp.domain.chapters

import com.example.privatebibleapp.core.Abstract

import com.example.privatebibleapp.presenter.chapters.ChapterUi

class ChapterDomain(private val chapterId:Int) : Abstract.Object<ChapterUi, ChapterDomainToUiMapper> {
    override fun map(mapper: ChapterDomainToUiMapper): ChapterUi =
        mapper.map(chapterId)
}