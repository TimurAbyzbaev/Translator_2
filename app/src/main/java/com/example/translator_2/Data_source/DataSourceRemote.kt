package com.example.translator_2.Data_source

import com.example.translator_2.api.DataModel
import com.example.translator_2.repository.RetrofitImplementation
import com.example.translator_2.repository.RoomDataBaseImplementation
import io.reactivex.Observable

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation =
        RetrofitImplementation()
) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation =
    RoomDataBaseImplementation()) :
        DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}