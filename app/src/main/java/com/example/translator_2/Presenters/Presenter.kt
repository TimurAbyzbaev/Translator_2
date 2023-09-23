package com.example.translator_2.Presenters

import com.example.translator_2.AppState
import com.example.translator_2.presentation.View.View

interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}