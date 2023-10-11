package com.example.privatebibleapp.domain.books

import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.presenter.books.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {

    fun map(id: Int, name: String): BookUi

    class Base(private val manageResources: ManageResources) : BookDomainToUiMapper {
        override fun map(id: Int, name: String): BookUi = when (id) {
                TypeTestament.NEW.getId() -> BookUi.Testament(
                    id,
                    manageResources.getString(R.string.new_testament)
                )
                TypeTestament.OLD.getId() -> BookUi.Testament(
                    id,
                    manageResources.getString(R.string.old_testament)
                )
                else -> BookUi.Base(id, name)
            }
    }
}