package com.lx.common.mvvm.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * @title：BaseViewModel
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/06
 */
typealias Block<T> = suspend (CoroutineScope) -> T
typealias Error = suspend (Exception) -> Unit
typealias Cancel = suspend (Exception) -> Unit

sealed class ViewState
object SucessState: ViewState()
class ErrorState(val errorMsg: String?): ViewState()
object LoadingState: ViewState()


open class BaseViewModel :ViewModel(){
    val uiState = MutableLiveData<ViewState>()

    fun <T> liveDataEx(block: suspend() -> T) = liveData{
        kotlin.runCatching {
            uiState.value = LoadingState
            block()
        }.onSuccess {
            emit(it)
            uiState.value = SucessState
        }.onFailure {
            uiState.value = ErrorState(it.message)
        }
    }

    fun <T> flowEx(block: suspend() -> T) = flow<T> {
        emit(block())
    }.onStart {
        uiState.value = LoadingState
    }.onCompletion {
        uiState.value = SucessState
    }.catch { it ->
        uiState.value = ErrorState(it.message)
    }.asLiveData()

    // UI
    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    // IO
    suspend fun<T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO){
            viewModelScope.launch { block() }
        }
    }

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @return Deferred<T>
     */
    protected fun <T> async(block: Block<T>): Deferred<T> {
        return viewModelScope.async { block.invoke(this) }
    }



}