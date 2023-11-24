package com.example.crispify

import android.app.Application
import com.example.crispify.data.AppContainer
import com.example.crispify.data.DefaultAppContainer

class CrispifyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}