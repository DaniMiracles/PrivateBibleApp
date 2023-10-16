package com.example.privatebibleapp.presenter.verses

import com.example.privatebibleapp.core.Abstract

class VersesUi(
    private val list: List<VerseUi>
) : Abstract.Object<Unit, VersesCommunication> {
    override fun map(mapper: VersesCommunication) = mapper.map(list)
}