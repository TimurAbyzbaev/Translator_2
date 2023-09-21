package com.example.translator_2.Interactors

import com.example.translator_2.AppState
import com.example.translator_2.Interactors.Interactor
import com.example.translator_2.api.DataModel
import com.example.translator_2.repository.Repository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>,
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if(fromRemoteSource){
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}