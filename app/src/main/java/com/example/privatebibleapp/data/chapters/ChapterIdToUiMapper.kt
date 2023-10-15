package com.example.privatebibleapp.data.chapters

import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.presenter.chapters.ChapterUi

interface ChapterIdToUiMapper : Abstract.Mapper {
    fun map(generatedId: Int, realId: Int): ChapterUi

    class Base(private val manageResources: ManageResources) : ChapterIdToUiMapper {
        override fun map(generatedId: Int, realId: Int): ChapterUi =
            ChapterUi.Base(realId, manageResources.getString(R.string.chapter_number, realId))
    }
}