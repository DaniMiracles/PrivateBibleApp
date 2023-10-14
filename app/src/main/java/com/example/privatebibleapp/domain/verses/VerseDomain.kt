package com.example.privatebibleapp.domain.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.presenter.verses.VerseUi

class VerseDomain(
    private val verseId: Int,
    private val text: String
) : Abstract.Object<VerseUi, VerseDomainToUiMapper> {
    override fun map(mapper: VerseDomainToUiMapper): VerseUi = mapper.map(verseId, text)

}