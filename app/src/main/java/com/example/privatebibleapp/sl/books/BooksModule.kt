package com.example.privatebibleapp.sl.books

import com.example.privatebibleapp.sl.core.BaseModule
import com.example.privatebibleapp.sl.core.CoreModule
import com.example.privatebibleapp.data.books.BookDataToDomainMapper
import com.example.privatebibleapp.data.books.BookRepository
import com.example.privatebibleapp.data.books.BooksDataToDomainMapper
import com.example.privatebibleapp.data.books.ToBookDataMapper
import com.example.privatebibleapp.data.books.cache.BookDataToDbMapper
import com.example.privatebibleapp.data.books.cache.BooksCacheDataSource
import com.example.privatebibleapp.data.books.cache.CacheMapperToBooks
import com.example.privatebibleapp.data.books.cloud.BooksCloudDataSource
import com.example.privatebibleapp.data.books.cloud.BooksServices
import com.example.privatebibleapp.data.books.cloud.CloudMapperToBooks
import com.example.privatebibleapp.domain.books.BookDomainToUiMapper
import com.example.privatebibleapp.domain.books.BooksDomainToUiMapper
import com.example.privatebibleapp.domain.books.BooksInteractor
import com.example.privatebibleapp.presenter.IdCache
import com.example.privatebibleapp.presenter.books.BooksCommunication
import com.example.privatebibleapp.presenter.books.BooksViewModel
import com.example.privatebibleapp.presenter.books.UiDataCache

class BooksModule(private val coreModule: CoreModule) : BaseModule<BooksViewModel> {
    override fun getViewModel(): BooksViewModel {
        val uiDataCache = getBooksUiDataCache()
        return BooksViewModel(
            getBooksInteractor(),
            getBookMapper(),
            getBooksCommunication(),
            uiDataCache,
            coreModule.bookCache,
            coreModule.navigator,
            coreModule.navigationCommunication
        )
    }


    private fun getBooksInteractor() = BooksInteractor.Base(
        getBookRepository(), BooksDataToDomainMapper.Base(BookDataToDomainMapper.Base())
    )

    private fun getBookRepository() = BookRepository.Base(
        BooksCloudDataSource.Base(
            coreModule.makeService(BooksServices::class.java),
            coreModule.gson
        ),
        CloudMapperToBooks.Base(ToBookDataMapper.Base()),
        BooksCacheDataSource.Base(coreModule.getDao().booksDao(), BookDataToDbMapper.Base()),
        CacheMapperToBooks.Base(ToBookDataMapper.Base())
    )

    private fun getBookMapper() = BooksDomainToUiMapper.Base(
        coreModule.manageResources,
        BookDomainToUiMapper.Base(coreModule.manageResources)
    )

    private fun getBooksUiDataCache() = UiDataCache.Base(IdCache.Base(coreModule.contextCore))

    private fun getBooksCommunication() = BooksCommunication.Base()
}