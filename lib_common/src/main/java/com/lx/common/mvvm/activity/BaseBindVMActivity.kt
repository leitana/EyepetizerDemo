package com.lx.common.mvvm.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lx.common.mvvm.viewmodel.BaseViewModel

/**
 * @title：BaseBindVMActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/07
 */
abstract class BaseBindVMActivity<VM: BaseViewModel, DB: ViewDataBinding>: BaseVMActivity<VM>() {

    lateinit var mBinding: DB

    override fun setLayout() {
        mBinding = DataBindingUtil.setContentView(
            this,
            getLayoutRes
        )
        mBinding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }
}