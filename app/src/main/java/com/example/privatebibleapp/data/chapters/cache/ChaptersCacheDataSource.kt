package com.example.privatebibleapp.data.chapters.cache


import com.example.privatebibleapp.data.chapters.ChapterData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface ChaptersCacheDataSource {

    suspend fun read(bookId: Int): List<ChapterDb>
    suspend fun saveChapters(bookId: Int, chaptersData: List<ChapterData>)

    class Base(
        private val chaptersDao: ChaptersDao,
        private val chapterDataToDbMapper: ChapterDataToDbMapper
    ) : ChaptersCacheDataSource {

        private val mutex = Mutex()
        override suspend fun read(bookId: Int): List<ChapterDb> = mutex.withLock {
            chaptersDao.getChaptersByBookId(bookId)
        }

        override suspend fun saveChapters(bookId: Int, chaptersData: List<ChapterData>) =
            mutex.withLock {
                val listChapterDb =
                    chaptersData.map { chapterData -> chapterData.mapToDb(chapterDataToDbMapper,bookId) }
                chaptersDao.insertChapters(listChapterDb)
            }
    }
}