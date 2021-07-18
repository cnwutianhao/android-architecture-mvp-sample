package com.tyhoo.mvp.kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tyhoo.mvp.kotlin.R
import com.tyhoo.mvp.kotlin.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = supportFragmentManager
            .findFragmentById(R.id.content_frame) as MainFragment? ?: MainFragment.newInstance()
            .also {
                replaceFragmentInActivity(it, R.id.content_frame)
            }

        // Create the presenter
        MainPresenter(this, mainFragment)
    }
}