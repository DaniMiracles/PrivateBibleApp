package com.example.privatebibleapp.sl.chapters

import com.example.privatebibleapp.sl.core.BaseModule
import com.example.privatebibleapp.sl.core.CoreModule
import com.example.privatebibleapp.data.chapters.ChapterDataToDomainMapper
import com.example.privatebibleapp.data.chapters.ChapterIdToUiMapper
import com.example.privatebibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.privatebibleapp.data.chapters.ChaptersRepository
import com.example.privatebibleapp.data.chapters.ToChapterDataMapper
import com.example.privatebibleapp.data.chapters.cache.CacheMapperToChapters
import com.example.privatebibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.privatebibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.privatebibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.privatebibleapp.data.chapters.cloud.ChaptersService
import com.example.privatebibleapp.data.chapters.cloud.CloudMapperToChapters
import com.example.privatebibleapp.domain.chapters.ChapterDomainToUiMapper
import com.example.privatebibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.example.privatebibleapp.domain.chapters.ChaptersInteractor
import com.example.privatebibleapp.presenter.chapters.ChaptersCommunication
import com.example.privatebibleapp.presenter.chapters.ChaptersViewModel

class ChaptersModule(private val coreModule: CoreModule) : BaseModule<ChaptersViewModel> {


    override fun getViewModel(): ChaptersViewModel = ChaptersViewModel(
        getChaptersInteractor(),
        getChaptersCommunication(),
        getChaptersMapper(),
        coreModule.navigator,
        coreModule.bookCache,
        coreModule.chapterCache,
        coreModule.navigationCommunication
    )

    private fun getChaptersInteractor() = ChaptersInteractor.Base(
        getChaptersRepository(), ChaptersDataToDomainMapper.Base(ChapterDataToDomainMapper.Base())
    )

    private fun getChaptersRepository() = ChaptersRepository.Base(
        ChaptersCloudDataSource.Base(
            coreModule.gson,
            coreModule.makeService(ChaptersService::class.java)
        ),
        CloudMapperToChapters.Base(ToChapterDataMapper.Cloud(coreModule.bookCache)),
        ChaptersCacheDataSource.Base(
            coreModule.getDao().chaptersDao(),
            ChapterDataToDbMapper.Base()
        ),
        CacheMapperToChapters.Base(ToChapterDataMapper.Cache(coreModule.bookCache)),
        coreModule.bookCache
    )

    private fun getChaptersCommunication() = ChaptersCommunication.Base()

    private fun getChaptersMapper() = ChaptersDomainToUiMapper.Base(
        coreModule.manageResources,
        ChapterDomainToUiMapper.Base(ChapterIdToUiMapper.Base(coreModule.manageResources))
    )
}