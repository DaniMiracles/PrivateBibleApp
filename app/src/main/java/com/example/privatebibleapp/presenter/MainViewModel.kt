package com.example.privatebibleapp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.privatebibleapp.core.Read

class MainViewModel(
    private val navigationCommunication: NavigationCommunication,
    private val navigator: Read<Int>
) : ViewModel() {

    fun init() {
        navigationCommunication.map(navigator.read())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        navigationCommunication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val currentScreen = navigator.read()
        val exit = currentScreen == 0
        if (!exit) {
            val newScreen = currentScreen - 1
            navigationCommunication.map(newScreen)
        }
        return exit
    }
}