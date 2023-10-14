package com.example.privatebibleapp.data.verses

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.verses.cloud.VerseData
import com.example.privatebibleapp.domain.verses.VersesDomain

abstract class VersesDataToDomainMapper :
    Abstract.Mapper.DataToDomain.Base<List<VerseData>, VersesDomain>() {

        class Base(): VersesDataToDomainMapper(){

            override fun map(data: List<VerseData>): VersesDomain {
                TODO("Not yet implemented")
            }
            override fun map(e: Exception): VersesDomain {
                TODO("Not yet implemented")
            }

        }
}