package com.example.privatebibleapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFactory<T : ViewModel>(private val baseModule: BaseModule<T>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = baseModule.getViewModel() as T
}