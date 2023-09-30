package com.example.privatebibleapp.dataBooks.cloud

import com.example.privatebibleapp.core.Abstract
import com.example.privatebibleapp.dataBooks.BookData
import com.example.privatebibleapp.dataBooks.ToBookDataMapper
import com.google.gson.annotations.SerializedName

/**
 * {"id":1,"name":"Genesis","testament":"OT","genre":{"id":1,"name":"Law"}
 */

data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("testament")
     private val testament : String
) : Abstract.Object<BookData,ToBookDataMapper>{
    override fun map(mapper: ToBookDataMapper): BookData = mapper.map(id, name,testament)
}





