package com.example.privatebibleapp.presenter

import com.example.privatebibleapp.core.Abstract

interface StringMapper : Abstract.Mapper {
    fun map(text: String)
}