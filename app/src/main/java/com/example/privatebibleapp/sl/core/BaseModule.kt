package com.example.privatebibleapp.sl.core

import androidx.lifecycle.ViewModel

interface BaseModule<T : ViewModel> {

    fun getViewModel(): T
}