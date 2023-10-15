package com.example.privatebibleapp.core

import androidx.lifecycle.ViewModel

interface BaseModule<T : ViewModel> {

    fun getViewModel(): T
}