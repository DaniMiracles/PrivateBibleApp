package com.example.privatebibleapp.presenter

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val navigationCommunication: NavigationCommunication,
    private val navigator: MainNavigator<Fragment>
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

    fun getFragment(id:Int): Fragment {
        return navigator.getFragment(id)
    }
}