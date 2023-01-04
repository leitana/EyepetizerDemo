package com.lx.eye_wan

import com.lx.common.mvvm.viewmodel.BaseViewModel
import com.lx.eye_wan.net.HomeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @title：WanViewModel
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/22
 */
@HiltViewModel
class WanViewModel @Inject constructor(private val homeApi: HomeApi): BaseViewModel() {

}