package com.example.repository.datasource

import com.example.model.dto.SearchResultDto

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation =
        RetrofitImplementation()
) :
    DataSource<List<SearchResultDto>> {
    override suspend fun getData(word: String): List<SearchResultDto> = remoteProvider.getData(word)
}

//class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation =
//    RoomDataBaseImplementation()
//) :
//    DataSource<List<DataModel>> {
//    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
//}