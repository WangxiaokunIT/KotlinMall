package com.chen.usercenter.ui.act

import android.os.Bundle
import android.view.View
import com.chen.usercenter.R
import com.chen.usercenter.presenter.LoginPresenter
import com.chen.usercenter.presenter.view.LoginView
import com.example.chen.baselibrary.ext.Onclick
import com.example.chen.baselibrary.ext.enable
import com.example.chen.baselibrary.ui.activity.BaseMvpActivity
import com.example.chen.usercenter.data.protocol.UserInfo
import com.example.chen.usercenter.injection.component.DaggerUserComponent
import com.example.chen.usercenter.injection.module.UserModule
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })
        mLoginBtn.setOnClickListener(this)
        mHeaderBar.getRightView().Onclick(this)
        mForgetPwdTv.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
            }
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not()
                && mPwdEt.text.isNullOrEmpty().not()

    }

    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(result)
        startActivity<UserInfoActivity>()
        finish()
    }
}
