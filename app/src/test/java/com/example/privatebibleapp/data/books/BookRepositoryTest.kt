package com.example.privatebibleapp.data.books

import com.example.privatebibleapp.data.books.cache.BookDb
import com.example.privatebibleapp.data.books.cache.BooksCacheDataSource
import com.example.privatebibleapp.data.books.cache.CacheMapperToBooks
import com.example.privatebibleapp.data.books.cloud.BookCloud
import com.example.privatebibleapp.data.books.cloud.BooksCloudDataSource
import com.example.privatebibleapp.data.books.cloud.CloudMapperToBooks
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BookRepositoryTest {
    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val booksCloudDataSource = TestBooksCloudDataSource(false)
        val booksCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BookRepository.Base(
            booksCloudDataSource,
            CloudMapperToBooks.Base(ToBookDataMapper.Base()),
            booksCacheDataSource,
            CacheMapperToBooks.Base(ToBookDataMapper.Base())
        )
        val actual = repository.fetchBooks()
        val expected = BooksData.Fail(unknownHostException)
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val booksCloudDataSource = TestBooksCloudDataSource(true)
        val booksCacheDataSource = TestBooksCacheDataSource(false)

        val repository = BookRepository.Base(
            booksCloudDataSource,
            CloudMapperToBooks.Base(ToBookDataMapper.Base()),
            booksCacheDataSource,
            CacheMapperToBooks.Base(ToBookDataMapper.Base())
        )
        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(1, "name1", "ot"),
                BookData(2, "name2", "nt")
            )
        )
        assertEquals(expected, actual)

    }

    inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            if (returnSuccess) {
                return listOf(
                    BookCloud(1, "name1", "ot"),
                    BookCloud(2, "name2", "nt")
                )
            } else {
                throw unknownHostException
            }
        }
    }

    inner class TestBooksCacheDataSource(
        private val returnSuccess: Boolean
    ) : BooksCacheDataSource {
        private val booksArray = ArrayList<BookDb>()

        override suspend fun read(): List<BookDb> {
            return if (returnSuccess)
                listOf(
                    BookDb(10, "name10", "ot"),
                    BookDb(20, "name20", "nt"),
                    BookDb(30, "name30", "0t")
                )
            else
                emptyList()
        }

        override suspend fun save(booksData: List<BookData>) {
            // not used here
        }
    }
}