package com.example.privatebibleapp.sl.verses

import com.example.privatebibleapp.sl.core.BaseModule
import com.example.privatebibleapp.sl.core.CoreModule
import com.example.privatebibleapp.data.verses.ToVerseDataMapper
import com.example.privatebibleapp.data.verses.VerseDataToDomainMapper
import com.example.privatebibleapp.data.verses.VersesDataToDomainMapper
import com.example.privatebibleapp.data.verses.VersesRepository
import com.example.privatebibleapp.data.verses.cache.CacheMapperToVerses
import com.example.privatebibleapp.data.verses.cache.VerseDataToDbMapper
import com.example.privatebibleapp.data.verses.cache.VersesCacheDataSource
import com.example.privatebibleapp.data.verses.cloud.CloudMapperToVerses
import com.example.privatebibleapp.data.verses.cloud.VersesCloudDataSource
import com.example.privatebibleapp.data.verses.cloud.VersesServices
import com.example.privatebibleapp.domain.verses.VerseDomainToUiMapper
import com.example.privatebibleapp.domain.verses.VersesDomainToUiMapper
import com.example.privatebibleapp.domain.verses.VersesInteractor
import com.example.privatebibleapp.presenter.verses.VersesCommunication
import com.example.privatebibleapp.presenter.verses.VersesViewModel

class VersesModule(private val coreModule: CoreModule) : BaseModule<VersesViewModel> {

    override fun getViewModel() = VersesViewModel(
        getInteractor(),
        getVersesMapper(),
        getVersesCommunication(),
        coreModule.navigator,
        coreModule.bookCache,
        coreModule.chapterCache
    )

    private fun getInteractor() = VersesInteractor.Base(
        getRepository(), VersesDataToDomainMapper.Base(VerseDataToDomainMapper.Base())
    )

    private fun getVersesMapper() =
        VersesDomainToUiMapper.Base(coreModule.manageResources, VerseDomainToUiMapper.Base())

    private fun getVersesCommunication() = VersesCommunication.Base()
    private fun getRepository() = VersesRepository.Base(
        VersesCloudDataSource.Base(
            coreModule.makeService(VersesServices::class.java),
            coreModule.gson
        ), CloudMapperToVerses.Base(ToVerseDataMapper.Base()),
        VersesCacheDataSource.Base(
            coreModule.getDao().versesDao(),
            VerseDataToDbMapper.Base()
        ),
        CacheMapperToVerses.Base(ToVerseDataMapper.Base()),
        coreModule.chapterCache,
        coreModule.bookCache
    )
}