package com.chen.BaseLibrary.common

import android.app.Application
import android.content.Context
import com.example.chen.baselibrary.injection.component.AppComponent
import com.example.chen.baselibrary.injection.component.DaggerAppComponent
import com.example.chen.baselibrary.injection.module.AppModule

open class BaseApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initInjection();
        context = this


    }

    companion object {
        lateinit var context: Context
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}