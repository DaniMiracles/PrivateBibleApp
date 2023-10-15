package com.example.privatebibleapp.core

import android.app.Application
import com.example.privatebibleapp.presenter.books.BookCache
import com.example.privatebibleapp.presenter.books.BooksCommunication
import com.example.privatebibleapp.presenter.books.BooksViewModel
import com.example.privatebibleapp.presenter.IdCache
import com.example.privatebibleapp.presenter.MainViewModel
import com.example.privatebibleapp.presenter.NavigationCommunication
import com.example.privatebibleapp.presenter.books.UiDataCache
import com.example.privatebibleapp.data.chapters.ChapterIdToUiMapper
import com.example.privatebibleapp.data.books.BookRepository
import com.example.privatebibleapp.data.books.BooksDataToDomainMapper
import com.example.privatebibleapp.data.books.ToBookDataMapper
import com.example.privatebibleapp.data.books.cache.BooksCacheDataSource
import com.example.privatebibleapp.data.books.cloud.BooksCloudDataSource
import com.example.privatebibleapp.data.books.cloud.BooksServices
import com.example.privatebibleapp.data.books.cloud.CloudMapperToBooks
import com.example.privatebibleapp.data.books.cache.BookDataToDbMapper
import com.example.privatebibleapp.data.books.cache.CacheMapperToBooks
import com.example.privatebibleapp.data.books.cache.CacheModule
import com.example.privatebibleapp.data.chapters.ChapterDataToDomainMapper
import com.example.privatebibleapp.data.chapters.ChaptersDataToDomainMapper
import com.example.privatebibleapp.data.chapters.ChaptersRepository
import com.example.privatebibleapp.data.chapters.ToChapterDataMapper
import com.example.privatebibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.example.privatebibleapp.data.chapters.cloud.ChaptersService
import com.example.privatebibleapp.data.chapters.cloud.CloudMapperToChapters
import com.example.privatebibleapp.data.books.BookDataToDomainMapper
import com.example.privatebibleapp.data.chapters.cache.CacheMapperToChapters
import com.example.privatebibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.example.privatebibleapp.data.chapters.cache.ChaptersCacheDataSource
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
import com.example.privatebibleapp.domain.books.BooksInteractor
import com.example.privatebibleapp.domain.chapters.ChapterDomainToUiMapper
import com.example.privatebibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.example.privatebibleapp.domain.chapters.ChaptersInteractor
import com.example.privatebibleapp.domain.books.BookDomainToUiMapper
import com.example.privatebibleapp.domain.books.BooksDomainToUiMapper
import com.example.privatebibleapp.domain.verses.VerseDomainToUiMapper
import com.example.privatebibleapp.domain.verses.VersesDomainToUiMapper
import com.example.privatebibleapp.domain.verses.VersesInteractor
import com.example.privatebibleapp.presenter.chapters.ChaptersCommunication
import com.example.privatebibleapp.presenter.chapters.ChaptersViewModel
import com.example.privatebibleapp.presenter.Navigator
import com.example.privatebibleapp.presenter.chapters.ChapterCache
import com.example.privatebibleapp.presenter.verses.VersesCommunication
import com.example.privatebibleapp.presenter.verses.VersesViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BibleApp : Application() {

    lateinit var mainViewModel: MainViewModel
    lateinit var booksViewModel: BooksViewModel
    lateinit var chaptersViewModel: ChaptersViewModel
    lateinit var versesViewModel: VersesViewModel

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    private val coreModule = CoreModule()

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)

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
        val booksCacheDataSource = BooksCacheDataSource.Base(booksDao, BookDataToDbMapper.Base())
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

        val bookCache = BookCache.Base(this)

        val chapterService = retrofit.create(ChaptersService::class.java)
        val chaptersCloudDataSource = ChaptersCloudDataSource.Base(gson, chapterService)
        val toChapterDataMapperCloud = ToChapterDataMapper.Cloud(bookCache)
        val cloudMapperToChapters = CloudMapperToChapters.Base(toChapterDataMapperCloud)
        val chapterDataToDomainMapper = ChapterDataToDomainMapper.Base()
        val chaptersDataToDomainMapper = ChaptersDataToDomainMapper.Base(chapterDataToDomainMapper)

        val bookDomainToUiMapper = BookDomainToUiMapper.Base(manageResources)
        val booksDomainToUiMapper =
            BooksDomainToUiMapper.Base(manageResources, bookDomainToUiMapper)

        val idCacheBase = IdCache.Base(this)
        val uiDataCache = UiDataCache.Base(idCacheBase)

        val chapterIdToUiMapper = ChapterIdToUiMapper.Base(manageResources)

        val chaptersCommunication = ChaptersCommunication.Base()
        val chapterDomainToUiMapper = ChapterDomainToUiMapper.Base(chapterIdToUiMapper)
        val chaptersDomainToUiMapper =
            ChaptersDomainToUiMapper.Base(manageResources, chapterDomainToUiMapper)

        val chaptersDao = dataBase.chaptersDao()
        val chapterDataToDbMapper = ChapterDataToDbMapper.Base()
        val chapterCacheDataSource =
            ChaptersCacheDataSource.Base(chaptersDao, chapterDataToDbMapper)


        val toChapterDataMapperCache = ToChapterDataMapper.Cache(bookCache)
        val cacheMapperToChapters = CacheMapperToChapters.Base(toChapterDataMapperCache)

        val chaptersRepository = ChaptersRepository.Base(
            chaptersCloudDataSource,
            cloudMapperToChapters,
            chapterCacheDataSource,
            cacheMapperToChapters,
            bookCache
        )

        val chaptersInteractor =
            ChaptersInteractor.Base(chaptersRepository, chaptersDataToDomainMapper)

        val verseService = retrofit.create(VersesServices::class.java)
        val versesCloudDataSource = VersesCloudDataSource.Base(verseService, gson)

        val toVerseDataMapper = ToVerseDataMapper.Base()
        val cloudMapperToVerses = CloudMapperToVerses.Base(toVerseDataMapper)

        val versesDao = dataBase.versesDao()
        val verseDataToDbMapper = VerseDataToDbMapper.Base()
        val versesCacheDataSource = VersesCacheDataSource.Base(versesDao, verseDataToDbMapper)

        val cacheMapperToVerses = CacheMapperToVerses.Base(toVerseDataMapper)
        val chapterCache = ChapterCache.Base(this)


        val versesRepository = VersesRepository.Base(
            versesCloudDataSource,
            cloudMapperToVerses,
            versesCacheDataSource,
            cacheMapperToVerses,
            chapterCache,
            bookCache
        )
        val verseDataToDomainMapper = VerseDataToDomainMapper.Base()
        val versesDataToDomainMapper = VersesDataToDomainMapper.Base(verseDataToDomainMapper)

        val versesInteractor = VersesInteractor.Base(versesRepository, versesDataToDomainMapper)

        val verseDomainToUiMapper = VerseDomainToUiMapper.Base()
        val versesDomainToUiMapper =
            VersesDomainToUiMapper.Base(manageResources, verseDomainToUiMapper)

        val versesCommunication = VersesCommunication.Base()


        val navigator = Navigator.Base(this)
        val navigationCommunication = NavigationCommunication.Base()



        mainViewModel = MainViewModel(navigationCommunication, navigator)

        booksViewModel = BooksViewModel(
            booksInteractor,
            booksDomainToUiMapper,
            booksCommunication,
            uiDataCache,
            bookCache, navigator, navigationCommunication
        )

        chaptersViewModel = ChaptersViewModel(
            chaptersInteractor,
            chaptersCommunication,
            chaptersDomainToUiMapper, navigator,
            bookCache,
            chapterCache, navigationCommunication
        )


        versesViewModel = VersesViewModel(
            versesInteractor,
            versesDomainToUiMapper,
            versesCommunication,
            navigator,
            bookCache,
            chapterCache
        )
    }


}