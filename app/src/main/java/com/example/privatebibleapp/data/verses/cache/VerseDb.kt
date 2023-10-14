package com.example.privatebibleapp.data.verses.cache

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.books.BookData
import com.example.privatebibleapp.data.books.ToBookDataMapper
import com.example.privatebibleapp.data.verses.ToVerseDataMapper
import com.example.privatebibleapp.data.verses.cloud.VerseData
import com.example.privatebibleapp.domain.verses.VerseDomain

@Entity(tableName = "verses_table")
class VerseDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "verse_Id") var verseId: Int,
    @ColumnInfo(name = "text") var text: String
) : Abstract.Object<VerseData, ToVerseDataMapper> {
    override fun map(mapper: ToVerseDataMapper): VerseData = mapper.map(id, verseId, text)

}


@Dao
interface VersesDao {


}