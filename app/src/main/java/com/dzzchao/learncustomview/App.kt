package com.dzzchao.learncustomview

import android.app.Application
import android.content.Context
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    companion object {
        lateinit var aContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())

        aContext = applicationContext
    }
}