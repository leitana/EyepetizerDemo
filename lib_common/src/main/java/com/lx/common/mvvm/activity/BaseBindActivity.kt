package com.lx.common.mvvm.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @title：BaseBindingActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/07
 */
abstract class BaseBindActivity<DB: ViewDataBinding>: AppCompatActivity() {
    abstract val getLayoutRes: Int
    lateinit var mBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutRes)

        initData()
    }
    open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }
}