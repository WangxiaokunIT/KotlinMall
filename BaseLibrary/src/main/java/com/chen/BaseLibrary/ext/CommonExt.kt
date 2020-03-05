package com.example.chen.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.chen.baselibrary.data.protocol.BaseResp
import com.example.chen.baselibrary.rx.BaseFunc
import com.example.chen.baselibrary.rx.BaseFuncBoolean
import com.example.chen.baselibrary.rx.BaseSubscriber
import com.example.chen.baselibrary.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 扩展Observable执行
 */
fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>,
                             lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

fun View.Onclick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.Onclick(method: () -> Unit) {
    this.setOnClickListener {
        method()
    }
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}


