package com.example.privatebibleapp.data.verses.cache

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
import com.example.privatebibleapp.data.chapters.cache.ChapterDb
import com.example.privatebibleapp.data.verses.ToVerseDataMapper
import com.example.privatebibleapp.data.verses.cloud.VerseData

@Entity(
    tableName = "verses_table",
    foreignKeys = [ForeignKey(
        entity = ChapterDb::class,
        parentColumns = ["chapterId"],
        childColumns = ["chapter_Id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("chapter_Id")]
)
class VerseDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "verse_Id") var verseId: Int,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "chapter_Id") var chapterId: Int,
) : Abstract.Object<VerseData, ToVerseDataMapper> {
    override fun map(mapper: ToVerseDataMapper): VerseData = mapper.map(id, verseId, text)

}


@Dao
interface VersesDao {

    @Query("SELECT * FROM verses_table WHERE chapter_Id = :chapterId ORDER BY verse_Id ASC")
    fun getVersesByChapterId(chapterId: Int): List<VerseDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerses(verses: List<VerseDb>)
}