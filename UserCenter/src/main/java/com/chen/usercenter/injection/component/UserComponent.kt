package com.example.chen.usercenter.injection.component

import com.chen.usercenter.ui.act.*
import com.example.chen.baselibrary.injection.PerComponentScope
import com.example.chen.baselibrary.injection.component.ActivityComponent
import com.example.chen.usercenter.injection.module.UserModule
import dagger.Component
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)],modules = [(UserModule::class)])
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}