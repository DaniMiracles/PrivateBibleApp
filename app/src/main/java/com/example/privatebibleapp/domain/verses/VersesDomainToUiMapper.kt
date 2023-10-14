package com.example.privatebibleapp.domain.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ErrorType
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.presenter.verses.VerseUi
import com.example.privatebibleapp.presenter.verses.VersesUi

abstract class VersesDomainToUiMapper(manageResources: ManageResources) :
    Abstract.Mapper.DomainToUi.Base<List<VerseDomain>, VersesUi>(manageResources) {

    class Base(
        manageResources: ManageResources,
        private val verseDomainToUiMapper: VerseDomainToUiMapper
    ) : VersesDomainToUiMapper(manageResources) {
        override fun map(data: List<VerseDomain>): VersesUi =
            VersesUi(data.map { verseDomain -> verseDomain.map(verseDomainToUiMapper) })

        override fun map(errorType: ErrorType): VersesUi =
            VersesUi(listOf(VerseUi.Fail(errorMessage(errorType))))

    }
}