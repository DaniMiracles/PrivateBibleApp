package com.example.privatebibleapp.domain.chapters

import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.presenter.chapters.ChapterUi

interface ChapterDomainToUiMapper : Abstract.Mapper {

    fun map(data: Int): ChapterUi

    class Base(
        private val manageResources: ManageResources
    ) : ChapterDomainToUiMapper {
        override fun map(data: Int): ChapterUi =
            ChapterUi.Base(data, manageResources.getString(R.string.chapter_number,data))
    }
}