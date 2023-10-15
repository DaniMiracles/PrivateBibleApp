package com.example.privatebibleapp.domain.verses

import com.example.privatebibleapp.data.verses.VersesDataToDomainMapper
import com.example.privatebibleapp.data.verses.VersesRepository

interface VersesInteractor {

    suspend fun fetchBooks(): VersesDomain

    class Base(
        private val versesRepository: VersesRepository,
        private val versesDataToDomainMapper: VersesDataToDomainMapper
    ) : VersesInteractor {
        override suspend fun fetchBooks(): VersesDomain {
            val versesData = versesRepository.fetchVerses()
            return versesData.map(versesDataToDomainMapper)
        }
    }
}