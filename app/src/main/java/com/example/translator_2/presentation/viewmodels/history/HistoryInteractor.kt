package com.example.translator_2.presentation.viewmodels.history

import com.example.translator_2.model.AppState
import com.example.translator_2.presentation.viewmodels.Interactor
import com.example.translator_2.model.data.DataModel
import com.example.translator_2.model.repository.Repository

class HistoryInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}