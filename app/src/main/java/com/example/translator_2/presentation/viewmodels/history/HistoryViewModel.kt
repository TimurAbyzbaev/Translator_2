package com.example.translator_2.presentation.viewmodels.history

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.translator_2.model.AppState
import com.example.translator_2.presentation.viewmodels.BaseViewModel
import com.example.translator_2.utils.parseLocalSearchResults
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    //переопределение метода из BaseViewModel
    override fun getData(word: String, isOnline: Boolean) {
        Log.d("#####", "Word is: $word")
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }


}