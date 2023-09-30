package com.example.privatebibleapp.dataBooks.cache

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.dataBooks.BookData
import com.example.privatebibleapp.dataBooks.ToBookDataMapper


@Entity(tableName = "bible_table")
data class BookDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "testament") val testament: String
) : Abstract.Object<BookData, ToBookDataMapper> {
    override fun map(mapper: ToBookDataMapper): BookData = mapper.map(id, name, testament)

}


@Dao
interface BooksDao {
    @Query("SELECT * FROM bible_table ORDER BY id ASC")
    fun fetchAllBooks(): List<BookDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(list: List<BookDb>)
}

