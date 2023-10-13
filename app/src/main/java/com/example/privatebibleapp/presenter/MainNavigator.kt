package com.example.privatebibleapp.presenter

import com.example.privatebibleapp.core.Read

interface MainNavigator<T> : Read<Int> {

    fun getFragment(id:Int) : T
}