package com.example.privatebibleapp.domain.chapters

import com.example.privatebibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.privatebibleapp.data.chapters.ChaptersRepository

interface ChaptersInteractor {

    suspend fun fetchChapters(): ChaptersDomain

    class Base(
        private val chaptersRepository: ChaptersRepository,
        private val chaptersDataToDomainMapper: ChaptersDataToDomainMapper
    ) : ChaptersInteractor {

        override suspend fun fetchChapters(): ChaptersDomain {
            val chaptersData = chaptersRepository.fetchChapters()
            return chaptersData.map(chaptersDataToDomainMapper)
        }

    }
}