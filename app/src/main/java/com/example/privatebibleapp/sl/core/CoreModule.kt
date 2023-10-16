package com.example.privatebibleapp.sl.core

import android.content.Context
import com.example.privatebibleapp.core.ManageResources
import com.example.privatebibleapp.data.books.cache.CacheModule
import com.example.privatebibleapp.presenter.MainViewModel
import com.example.privatebibleapp.presenter.NavigationCommunication
import com.example.privatebibleapp.presenter.Navigator
import com.example.privatebibleapp.presenter.books.BookCache
import com.example.privatebibleapp.presenter.chapters.ChapterCache
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoreModule : BaseModule<MainViewModel> {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var manageResources: ManageResources
    lateinit var gson: Gson
    lateinit var navigator: Navigator
    lateinit var navigationCommunication: NavigationCommunication
    lateinit var bookCache: BookCache
    lateinit var chapterCache: ChapterCache
    private lateinit var dataBase: CacheModule
    lateinit var contextCore: Context
    private lateinit var retrofit: Retrofit


    fun init(context: Context) {
        dataBase = CacheModule.Base(context)

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        manageResources = ManageResources.Base(context)
        gson = Gson()
        navigator = Navigator.Base(context)
        navigationCommunication = NavigationCommunication.Base()
        bookCache = BookCache.Base(context)
        chapterCache = ChapterCache.Base(context)
        contextCore = context

    }

    fun <T> makeService(clazz: Class<T>): T =
        retrofit.create(clazz)

    fun getDao() = dataBase.provideDatabase()

    override fun getViewModel(): MainViewModel =
        MainViewModel(navigationCommunication, navigator)


}