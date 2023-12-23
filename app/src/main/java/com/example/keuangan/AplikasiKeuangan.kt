package com.example.keuangan

import android.app.Application
import com.example.keuangan.repositori.ContainerApp
import com.example.keuangan.repositori.ContainerDataApp


class AplikasiKeuangan: Application() {
    lateinit var container: ContainerApp
    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}