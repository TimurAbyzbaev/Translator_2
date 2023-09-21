package com.example.translator_2.repository

import com.example.translator_2.Data_source.DataSource
import com.example.translator_2.api.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation: DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }

}