package com.example.translator_2.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.translator_2.model.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> =
        MutableLiveData()
) : ViewModel() {
    // Объявляем свой собственный скоуп
    // В качестве аргумента передается CoroutineContext, который мы составляем
    // через "+" из трех частей:
    // - Dispatchers.Main говорит, что результат работы предназначен для
    // основного потока;
    // - SupervisorJob() позволяет всем дочерним корутинам выполняться
    // независимо, то есть, если какая-то корутина упадёт с ошибкой, остальные
    // будут выполнены нормально;
    // - CoroutineExceptionHandler позволяет перехватывать и отрабатывать
    // ошибки и краши

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                    + SupervisorJob()
                    + CoroutineExceptionHandler {_, throwable -> handleError(throwable)}
    )

    abstract fun getData(word: String, isOnline: Boolean)
    abstract fun handleError(error: Throwable)
    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
    protected fun cancelJob(){
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

}