package com.example.privatebibleapp.core

import android.app.Application
import com.example.privatebibleapp.BooksCommunication
import com.example.privatebibleapp.BooksViewModel
import com.example.privatebibleapp.IdCache
import com.example.privatebibleapp.UiDataCache
import com.example.privatebibleapp.dataBooks.BookRepository
import com.example.privatebibleapp.dataBooks.BooksDataToDomainMapper
import com.example.privatebibleapp.dataBooks.ToBookDataMapper
import com.example.privatebibleapp.dataBooks.cache.BooksCacheDataSource
import com.example.privatebibleapp.dataBooks.cloud.BooksCloudDataSource
import com.example.privatebibleapp.dataBooks.cloud.BooksServices
import com.example.privatebibleapp.dataBooks.CloudMapperToBooks
import com.example.privatebibleapp.dataBooks.cache.BookDataToDbMapper
import com.example.privatebibleapp.dataBooks.cache.CacheMapperToBooks
import com.example.privatebibleapp.dataBooks.cache.CacheModule
import com.example.privatebibleapp.domainBooks.BookDataToDomainMapper
import com.example.privatebibleapp.domainBooks.BooksInteractor
import com.example.privatebibleapp.presenterBooks.BookDomainToUiMapper
import com.example.privatebibleapp.presenterBooks.BooksDomainToUiMapper
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BibleApp : Application() {

    lateinit var booksViewModel: BooksViewModel

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    override fun onCreate() {
        super.onCreate()
        val dataBase by lazy { CacheModule.Base(this).provideDatabase() }


        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val bookService = retrofit.create(BooksServices::class.java)
        val toBookDataMapper = ToBookDataMapper.Base()

        val gson = Gson()
        val booksDao = dataBase.booksDao()
        val booksCloudDataSource = BooksCloudDataSource.Base(bookService, gson)
        val cloudMapperToBooks = CloudMapperToBooks.Base(toBookDataMapper)
        val booksCacheDataSource = BooksCacheDataSource.Base(booksDao,BookDataToDbMapper.Base())
        val cacheMapperToBooks = CacheMapperToBooks.Base(toBookDataMapper)


        val bookRepository = BookRepository.Base(
            booksCloudDataSource,
            cloudMapperToBooks,
            booksCacheDataSource,
            cacheMapperToBooks
        )
        val booksInteractor = BooksInteractor.Base(
            bookRepository,
            BooksDataToDomainMapper.Base(BookDataToDomainMapper.Base())
        )
        val manageResources = ManageResources.Base(this)
        val booksCommunication = BooksCommunication.Base()

        booksViewModel = BooksViewModel(
            booksInteractor,
            BooksDomainToUiMapper.Base(manageResources, BookDomainToUiMapper.Base(manageResources)),
            booksCommunication,
            UiDataCache.Base(IdCache.Base(this))
        )


    }
}