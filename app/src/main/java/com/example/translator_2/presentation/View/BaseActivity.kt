package com.example.translator_2.presentation.View


import androidx.appcompat.app.AppCompatActivity
import com.example.translator_2.AppState
import com.example.translator_2.presentation.viewmodels.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {
    //В каждой активити будет своя ViewModel, которая наслудуется от BaseViewModel
    abstract  val model: BaseViewModel<T>
    //Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract  fun renderData(appState: T)
}