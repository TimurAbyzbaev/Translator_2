package com.example.translator_2.presentation.viewmodels

import androidx.lifecycle.LiveData
import com.example.translator_2.AppState
import com.example.translator_2.Data_source.DataSourceLocal
import com.example.translator_2.Data_source.DataSourceRemote
import com.example.translator_2.Interactors.MainInteractor
import com.example.translator_2.repository.RepositoryImplementation
import io.reactivex.observers.DisposableObserver

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    )
) : BaseViewModel<AppState>() {
    private var appState: AppState? = null

    //переопределение метода из BaseViewModel
    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return  super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            // Данные успешно загружены; сохраняем их и передаем во View (через
            // LiveData). View сама разберётся, как их отображать
            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.postValue(state) // или liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.postValue(AppState.Error(e))
            }

            override fun onComplete() {}
        }
    }

}