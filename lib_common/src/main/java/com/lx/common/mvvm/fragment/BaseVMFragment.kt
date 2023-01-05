package com.lx.common.mvvm.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.lx.common.mvvm.viewmodel.*
import com.lx.lib_base.ext.toastError
import com.orhanobut.logger.Logger
import java.lang.reflect.ParameterizedType

/**
 * @title：BaseVMFragment
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/22
 */
abstract class BaseVMFragment<VM: BaseViewModel>: BaseFragment() {
    lateinit var viewModel: VM

    override fun initData() {
        initViewModel()
        lazyLoadData()
    }

    private fun initViewModel(){
        val parameterizedType = javaClass.genericSuperclass as? ParameterizedType
        @Suppress("UNCHECKED_CAST")
        val vmClass = parameterizedType?.actualTypeArguments?.getOrNull(0) as? Class<VM>?
        if(vmClass != null)
            viewModel = ViewModelProvider(this).get( vmClass )
        else
            Logger.d("BaseVMActivity","could not find VM class for $this")
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

    protected fun <T : Any> LiveData<T>.observerKt(block: (T) -> Unit){
        this.observe(viewLifecycleOwner){
            block(it)
        }
    }

    //由于每个页面的加载框可能不一致，所以此处暴露给子类实现
    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    open fun handlerError() {

    }

    abstract fun lazyLoadData()
}