package com.example.translator_2.repository

import com.example.translator_2.Data_source.DataSource
import com.example.translator_2.api.DataModel
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}