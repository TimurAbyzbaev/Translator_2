package com.example.translator_2.model.Data_source

import com.example.translator_2.model.data.DataModel

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation =
        RetrofitImplementation()
) :
    DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation =
    RoomDataBaseImplementation()
) :
    DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}