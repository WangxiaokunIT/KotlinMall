package com.chen.usercenter.repository

import com.chen.usercenter.data.api.UploadApi
import com.example.chen.baselibrary.data.net.RetrofitFactory
import com.example.chen.baselibrary.data.protocol.BaseResp
import rx.Observable
import javax.inject.Inject

class UploadTokenRepository @Inject constructor(){
    fun getUploadToken():Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }

}