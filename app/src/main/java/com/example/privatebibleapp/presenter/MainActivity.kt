package com.example.privatebibleapp.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.privatebibleapp.R
import com.example.privatebibleapp.core.BibleApp


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = (application as BibleApp).getMainViewModel()

        mainViewModel.observe(this, Observer { typeScreen ->
            val fragment = mainViewModel.getFragment(typeScreen)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        })
        mainViewModel.init()
    }

    override fun onBackPressed() {
        if (mainViewModel.navigateBack())
            super.onBackPressed()
    }
}