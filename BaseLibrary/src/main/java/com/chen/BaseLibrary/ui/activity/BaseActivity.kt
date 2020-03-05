package com.example.chen.baselibrary.ui.activity

import android.os.Bundle
import com.chen.BaseLibrary.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

open class BaseActivity:RxAppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instarnce.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instarnce.finishActivity(this)
    }
}
