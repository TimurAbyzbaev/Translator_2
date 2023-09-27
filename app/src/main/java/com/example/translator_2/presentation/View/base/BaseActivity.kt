package com.example.translator_2.presentation.View.base


import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.translator_2.model.AppState
import com.example.translator_2.presentation.viewmodels.Interactor
import com.example.translator_2.R
import com.example.translator_2.presentation.viewmodels.BaseViewModel
import com.example.translator_2.utils.network.isOnline
import com.example.translator_2.utils.ui.AlertDialogFragment

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {
    //В каждой активити будет своя ViewModel, которая наслудуется от BaseViewModel
    abstract  val model: BaseViewModel<T>
    //Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract  fun renderData(appState: T)

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if(!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}