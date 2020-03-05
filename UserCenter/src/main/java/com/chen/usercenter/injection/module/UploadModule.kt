package com.chen.usercenter.injection.module

import com.chen.usercenter.service.impl.UploadServiceImpl
import com.kotlin.user.service.UploadService
import dagger.Module
import dagger.Provides

/*
    上传Module
 */
@Module
class UploadModule {

    @Provides
    fun provideUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }

}