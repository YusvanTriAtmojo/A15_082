package com.example.uaspam

import android.app.Application
import com.example.uaspam.container.AppContainer
import com.example.uaspam.container.HewanContainer

class HewanApplications : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = HewanContainer()
    }
}