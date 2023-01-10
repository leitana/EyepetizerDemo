package com.lx.common.mvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.lx.common.mvvm.viewmodel.*
import com.lx.lib_base.ext.getVmClazz
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
abstract class BaseVMFragment<DB: ViewDataBinding, VM: BaseViewModel>: BaseFragment() {
    lateinit var binding: DB
    lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutRes,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initData() {
        initViewModel()
        lazyLoadData()
    }

    private fun initViewModel(){
//        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
//        @Suppress("UNCHECKED_CAST")
//        val vmClass = parameterizedType?.actualTypeArguments?.getOrNull(0) as? Class<VM>?
//        if(vmClass != null)
//            viewModel = ViewModelProvider(this)[vmClass]
//        else
//            Logger.d("BaseVMActivity","could not find VM class for $this")

//        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
//        mViewModel = ViewModelProvider(this)[parameterizedType.actualTypeArguments[0] as Class<VM>]

        mViewModel = createViewModel()

        mViewModel.uiState.observe(this){ state ->
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

    private fun createViewModel(): VM{
        return ViewModelProvider(this).get(getVmClazz(this))
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