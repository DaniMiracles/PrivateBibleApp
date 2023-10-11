package com.example.privatebibleapp.data.chapters

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.domain.chapters.ChapterDomain

interface ChapterDataToDomainMapper : Abstract.Mapper {
    fun map(chapterId : Int) : ChapterDomain

    class Base() : ChapterDataToDomainMapper{
        override fun map(chapterId: Int): ChapterDomain = ChapterDomain(chapterId)
    }
}