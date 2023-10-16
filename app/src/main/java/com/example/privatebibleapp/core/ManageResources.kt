package com.example.privatebibleapp.core

import android.content.Context
import androidx.annotation.StringRes

interface ManageResources {
    fun getString(@StringRes id: Int) : String
    fun getString(@StringRes id: Int,idText : Int) : String

    class Base(private val context: Context) : ManageResources{
        override fun getString(@StringRes id: Int) = context.getString(id)
        override fun getString(id: Int, idText: Int): String = context.getString(id,idText)
    }
}