package com.example.chen.provider.common

import com.chen.BaseLibrary.common.Constant
import com.example.chen.baselibrary.utils.AppPrefsUtils

/*
    顶级函数，判断是否登录
 */
fun isLogined(): Boolean {
    return AppPrefsUtils.getString(Constant.KEY_SP_TOKEN).isNotEmpty()
}
