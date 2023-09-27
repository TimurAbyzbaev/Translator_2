package com.example.translator_2.model.Data_source

import com.example.translator_2.model.data.DataModel

class RoomDataBaseImplementation: DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }

}