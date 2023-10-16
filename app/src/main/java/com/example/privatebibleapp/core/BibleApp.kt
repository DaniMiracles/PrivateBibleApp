package com.example.privatebibleapp.core

import android.app.Application
import com.example.privatebibleapp.sl.books.BooksFactory
import com.example.privatebibleapp.sl.books.BooksModule
import com.example.privatebibleapp.sl.chapters.ChaptersFactory
import com.example.privatebibleapp.sl.chapters.ChaptersModule
import com.example.privatebibleapp.sl.core.CoreModule
import com.example.privatebibleapp.sl.verses.VersesFactory
import com.example.privatebibleapp.sl.verses.VersesModule

class BibleApp : Application() {

//    lateinit var mainViewModel: MainViewModel
//    lateinit var booksViewModel: BooksViewModel
//    lateinit var chaptersViewModel: ChaptersViewModel
//    lateinit var versesViewModel: VersesViewModel
//
//    private companion object {
//        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
//    }

    private val coreModule = CoreModule()

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)

//        val dataBase by lazy { CacheModule.Base(this).provideDatabase() }
//
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        val bookService = retrofit.create(BooksServices::class.java)
//        val toBookDataMapper = ToBookDataMapper.Base()
//
//        val gson = Gson()
//        val booksDao = dataBase.booksDao()
//
//        val booksCloudDataSource = BooksCloudDataSource.Base(bookService, gson)
//        val cloudMapperToBooks = CloudMapperToBooks.Base(toBookDataMapper)
//        val booksCacheDataSource = BooksCacheDataSource.Base(booksDao, BookDataToDbMapper.Base())
//        val cacheMapperToBooks = CacheMapperToBooks.Base(toBookDataMapper)
//
//
//        val bookRepository = BookRepository.Base(
//            booksCloudDataSource,
//            cloudMapperToBooks,
//            booksCacheDataSource,
//            cacheMapperToBooks
//        )
//        val booksInteractor = BooksInteractor.Base(
//            bookRepository,
//            BooksDataToDomainMapper.Base(BookDataToDomainMapper.Base())
//        )
//        val manageResources = ManageResources.Base(this)
//        val booksCommunication = BooksCommunication.Base()
//
//        val bookCache = BookCache.Base(this)
//
//        val chapterService = retrofit.create(ChaptersService::class.java)
//        val chaptersCloudDataSource = ChaptersCloudDataSource.Base(gson, chapterService)
//        val toChapterDataMapperCloud = ToChapterDataMapper.Cloud(bookCache)
//        val cloudMapperToChapters = CloudMapperToChapters.Base(toChapterDataMapperCloud)
//        val chapterDataToDomainMapper = ChapterDataToDomainMapper.Base()
//        val chaptersDataToDomainMapper = ChaptersDataToDomainMapper.Base(chapterDataToDomainMapper)
//
//        val bookDomainToUiMapper = BookDomainToUiMapper.Base(manageResources)
//        val booksDomainToUiMapper =
//            BooksDomainToUiMapper.Base(manageResources, bookDomainToUiMapper)
//
//        val idCacheBase = IdCache.Base(this)
//        val uiDataCache = UiDataCache.Base(idCacheBase)
//
//        val chapterIdToUiMapper = ChapterIdToUiMapper.Base(manageResources)
//
//        val chaptersCommunication = ChaptersCommunication.Base()
//        val chapterDomainToUiMapper = ChapterDomainToUiMapper.Base(chapterIdToUiMapper)
//        val chaptersDomainToUiMapper =
//            ChaptersDomainToUiMapper.Base(manageResources, chapterDomainToUiMapper)
//
//        val chaptersDao = dataBase.chaptersDao()
//        val chapterDataToDbMapper = ChapterDataToDbMapper.Base()
//        val chapterCacheDataSource =
//            ChaptersCacheDataSource.Base(chaptersDao, chapterDataToDbMapper)
//
//
//        val toChapterDataMapperCache = ToChapterDataMapper.Cache(bookCache)
//        val cacheMapperToChapters = CacheMapperToChapters.Base(toChapterDataMapperCache)
//
//        val chaptersRepository = ChaptersRepository.Base(
//            chaptersCloudDataSource,
//            cloudMapperToChapters,
//            chapterCacheDataSource,
//            cacheMapperToChapters,
//            bookCache
//        )
//
//        val chaptersInteractor =
//            ChaptersInteractor.Base(chaptersRepository, chaptersDataToDomainMapper)
//
//        val verseService = retrofit.create(VersesServices::class.java)
//        val versesCloudDataSource = VersesCloudDataSource.Base(verseService, gson)
//
//        val toVerseDataMapper = ToVerseDataMapper.Base()
//        val cloudMapperToVerses = CloudMapperToVerses.Base(toVerseDataMapper)
//
//        val versesDao = dataBase.versesDao()
//        val verseDataToDbMapper = VerseDataToDbMapper.Base()
//        val versesCacheDataSource = VersesCacheDataSource.Base(versesDao, verseDataToDbMapper)
//
//        val cacheMapperToVerses = CacheMapperToVerses.Base(toVerseDataMapper)
//        val chapterCache = ChapterCache.Base(this)
//
//
//        val versesRepository = VersesRepository.Base(
//            versesCloudDataSource,
//            cloudMapperToVerses,
//            versesCacheDataSource,
//            cacheMapperToVerses,
//            chapterCache,
//            bookCache
//        )
//        val verseDataToDomainMapper = VerseDataToDomainMapper.Base()
//        val versesDataToDomainMapper = VersesDataToDomainMapper.Base(verseDataToDomainMapper)
//
//        val versesInteractor = VersesInteractor.Base(versesRepository, versesDataToDomainMapper)
//
//        val verseDomainToUiMapper = VerseDomainToUiMapper.Base()
//        val versesDomainToUiMapper =
//            VersesDomainToUiMapper.Base(manageResources, verseDomainToUiMapper)
//
//        val versesCommunication = VersesCommunication.Base()
//
//
//        val navigator = Navigator.Base(this)
//        val navigationCommunication = NavigationCommunication.Base()
//
//
//
//        mainViewModel = MainViewModel(navigationCommunication, navigator)
//
//        booksViewModel = BooksViewModel(
//            booksInteractor,
//            booksDomainToUiMapper,
//            booksCommunication,
//            uiDataCache,
//            bookCache, navigator, navigationCommunication
//        )
//
//        chaptersViewModel = ChaptersViewModel(
//            chaptersInteractor,
//            chaptersCommunication,
//            chaptersDomainToUiMapper, navigator,
//            bookCache,
//            chapterCache, navigationCommunication
//        )
//
//
//        versesViewModel = VersesViewModel(
//            versesInteractor,
//            versesDomainToUiMapper,
//            versesCommunication,
//            navigator,
//            bookCache,
//            chapterCache
//        )
    }

    fun getMainViewModel() =
        coreModule.getViewModel()
    fun chaptersFactory() = ChaptersFactory(ChaptersModule(coreModule))
    fun booksFactory() = BooksFactory(BooksModule(coreModule))
    fun versesFactory() = VersesFactory(VersesModule(coreModule))
}