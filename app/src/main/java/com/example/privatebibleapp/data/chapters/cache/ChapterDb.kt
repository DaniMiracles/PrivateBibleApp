package com.example.privatebibleapp.data.chapters.cache

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.books.cache.BookDb
import com.example.privatebibleapp.data.chapters.ChapterData
import com.example.privatebibleapp.data.chapters.ToChapterDataMapper


@Entity(
    tableName = "chapters_table",
    foreignKeys = [ForeignKey(
        entity = BookDb::class,
        parentColumns = ["id"],
        childColumns = ["book_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("book_id")]
)
data class ChapterDb(
    @PrimaryKey
    @ColumnInfo(name = "chapterId")
    val chapterId: Int,
    @ColumnInfo(name = "book_id")
    val bookId: Int
) : Abstract.Object<ChapterData, ToChapterDataMapper> {
    override fun map(mapper: ToChapterDataMapper): ChapterData = mapper.map(chapterId)
}


@Dao
interface ChaptersDao {

    @Query("SELECT * FROM chapters_table WHERE book_id = :bookId ORDER BY chapterId ASC")
    fun getChaptersByBookId(bookId: Int): List<ChapterDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChapters(chapters: List<ChapterDb>)
}