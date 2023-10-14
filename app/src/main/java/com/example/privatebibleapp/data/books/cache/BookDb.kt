package com.example.privatebibleapp.data.books.cache

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.data.books.BookData
import com.example.privatebibleapp.data.books.ToBookDataMapper


@Entity(tableName = "bible_table")
data class BookDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var bookId: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "testament") var testament: String
) : Abstract.Object<BookData, ToBookDataMapper> {
    override fun map(mapper: ToBookDataMapper): BookData = mapper.map(bookId, name, testament)

}


@Dao
interface BooksDao {
    @Query("SELECT * FROM bible_table ORDER BY id ASC")
    fun fetchAllBooks(): List<BookDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(list: List<BookDb>)
}

