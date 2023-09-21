package com.example.translator_2.Presenters

import com.example.translator_2.AppState
import com.example.translator_2.Data_source.DataSourceLocal
import com.example.translator_2.Data_source.DataSourceRemote
import com.example.translator_2.Interactors.MainInteractor
import com.example.translator_2.View.View
import com.example.translator_2.repository.RepositoryImplementation
import com.example.translator_2.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MainPresenterImpl<T: AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schdulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {
    private var currentView: V? = null
    override fun attachView(view: V) {
        if(view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if(view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schdulerProvider.io())
                .observeOn(schdulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {}
        }
    }
}