package com.example.chen.usercenter.service.impl

import com.example.chen.baselibrary.data.protocol.BaseResp
import com.example.chen.baselibrary.ext.convert
import com.example.chen.baselibrary.ext.convertBoolean
import com.example.chen.baselibrary.rx.BaseException
import com.example.chen.usercenter.data.protocol.UserInfo
import com.example.chen.usercenter.repository.UserRepository
import com.example.chen.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {
    @Inject
    lateinit var repository:UserRepository
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode).
                convertBoolean();
    }
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId).
                convert();
    }
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile, verifyCode).
                convertBoolean();
    }
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile, pwd).
                convertBoolean();
    }
    /*
      修改用户资料
   */
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return repository.editUser(userIcon,userName,userGender,userSign).convert()
    }
}