package com.example.privatebibleapp.data.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.verses.cloud.VerseData
import com.example.privatebibleapp.domain.verses.VersesDomain

sealed class VersesData : Abstract.Object<VersesDomain, VersesDataToDomainMapper> {

    data class Success(private val versesData: List<VerseData>) : VersesData() {
        override fun map(mapper: VersesDataToDomainMapper): VersesDomain =
            mapper.map(versesData)
    }

    data class Fail(private val exception: Exception) : VersesData() {
        override fun map(mapper: VersesDataToDomainMapper): VersesDomain = mapper.map(exception)
    }
}