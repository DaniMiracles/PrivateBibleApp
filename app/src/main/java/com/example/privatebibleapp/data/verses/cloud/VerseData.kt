package com.example.privatebibleapp.data.verses.cloud

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.verses.VerseDataToDomainMapper
import com.example.privatebibleapp.data.verses.cache.VerseDataToDbMapper
import com.example.privatebibleapp.data.verses.cache.VerseDb
import com.example.privatebibleapp.domain.verses.VerseDomain

class VerseData(
    private val id: Int,
    private val verseId: Int,
    private val text: String
) : Abstract.Object<VerseDomain, VerseDataToDomainMapper>,
    Abstract.Database<VerseDb, VerseDataToDbMapper> {

    override fun map(mapper: VerseDataToDomainMapper): VerseDomain = mapper.map(verseId, text)

    override fun mapToDb(mapper: VerseDataToDbMapper): VerseDb =
        mapper.mapToDb(id, verseId, text)
}