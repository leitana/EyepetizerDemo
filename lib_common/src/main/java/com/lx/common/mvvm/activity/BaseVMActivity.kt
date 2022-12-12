package com.lx.common.mvvm.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lx.common.mvvm.viewmodel.BaseViewModel
import com.lx.common.mvvm.viewmodel.ErrorState
import com.lx.common.mvvm.viewmodel.LoadingState
import com.lx.common.mvvm.viewmodel.SucessState
import com.lx.lib_base.ext.toastError
import com.orhanobut.logger.Logger
import java.lang.reflect.ParameterizedType

/**
 * @title：BaseVMActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/07
 */
abstract class BaseVMActivity<VM : BaseViewModel>: AppCompatActivity() {
    abstract val getLayoutRes: Int
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayout()
        initView()
        initViewModel()
        initData()
        startObserve()
    }

    open fun setLayout() {
        setContentView(getLayoutRes)
    }

    private fun initViewModel(){
        val parameterizedType = javaClass.genericSuperclass as? ParameterizedType
        @Suppress("UNCHECKED_CAST")
        val vmClass = parameterizedType?.actualTypeArguments?.getOrNull(0) as? Class<VM>?
        if(vmClass != null)
            viewModel = ViewModelProvider(this).get( vmClass )
        else
            Logger.d("BaseFragment","could not find VM class for $this")
        viewModel.uiState.observe(this){ state ->
            when(state) {
                LoadingState -> {
                    showLoading()
                }
                SucessState -> {
                    hideLoading()
                }
                is ErrorState -> {
                    hideLoading()
                    state.errorMsg?.let { toastError(it) }
                    handlerError()
                }
            }
        }
    }

    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    open fun handlerError() {

    }

    open fun initWindow() {

    }

    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}