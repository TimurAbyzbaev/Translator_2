package com.example.translator_2.repository

import com.example.translator_2.Data_source.DataSource
import com.example.translator_2.api.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation: DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }

}