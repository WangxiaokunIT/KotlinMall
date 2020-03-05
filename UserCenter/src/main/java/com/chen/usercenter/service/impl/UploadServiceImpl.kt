package com.chen.usercenter.service.impl

import com.chen.usercenter.repository.UploadTokenRepository
import com.example.chen.baselibrary.ext.convert
import com.kotlin.user.service.UploadService
import rx.Observable
import javax.inject.Inject

class UploadServiceImpl @Inject constructor(): UploadService {

    @Inject
    lateinit var repository: UploadTokenRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }

}