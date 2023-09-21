package com.example.translator_2.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.translator_2.AppState
import com.example.translator_2.Presenters.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {
    //Храним ссылку на presenter
    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    //Когда View готова отображать данные, передаем ссылку на View в presenter
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    //При пересоздании или уничтожении View удаляем ссылку, иначе в presenter будет
// ссылка на несуществующую View
    override fun onStop() {
        presenter.detachView(this)
        super.onStop()
    }
}