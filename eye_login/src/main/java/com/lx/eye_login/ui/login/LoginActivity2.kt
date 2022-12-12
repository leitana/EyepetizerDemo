package com.lx.eye_login.ui.login

import com.lx.common.mvvm.activity.BaseBindVMActivity
import com.lx.eye_login.R
import com.lx.eye_login.databinding.LoginActivityTest1Binding

class LoginActivity2: BaseBindVMActivity<HomeViewModel, LoginActivityTest1Binding>(){
    override val getLayoutRes: Int
        get() = R.layout.login_activity_test1

    override fun initView() {
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}