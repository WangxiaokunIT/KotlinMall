package com.chen.usercenter.ui.act

import android.os.Bundle
import android.view.View
import com.chen.BaseLibrary.common.AppManager
import com.chen.usercenter.R
import com.chen.usercenter.presenter.RegisterPresenter
import com.chen.usercenter.presenter.view.RegisterView
import com.example.chen.baselibrary.ext.enable
import com.example.chen.baselibrary.ui.activity.BaseMvpActivity
import com.example.chen.usercenter.injection.component.DaggerUserComponent
import com.example.chen.usercenter.injection.module.UserModule
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity:BaseMvpActivity<RegisterPresenter>(),RegisterView,View.OnClickListener {
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build()
                .inject(this)
        mPresenter.mView=this
    }

    private var  pressTime:Long = 0;
    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
    }
    private fun initView() {
        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})
        mVerifyCodeBtn.setOnClickListener(this)
        mRegisterBtn.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.mVerifyCodeBtn ->{
                mVerifyCodeBtn.requestSendVerifyNumber()
            }
            R.id.mRegisterBtn->{
                mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString());
            }
        }
    }
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not()
                &&mVerifyCodeEt.text.isNullOrEmpty().not()
                &&mPwdEt.text.isNullOrEmpty().not()
                &&mPwdConfirmEt.text.isNullOrEmpty().not()
    }
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time-pressTime>2000){
            toast("再按一次退出程序")
            pressTime=time
        }else{
            AppManager.instarnce.exitApp(this)
        }
    }

}