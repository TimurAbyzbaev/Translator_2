package com.example.translator_2.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    //Возвращаем Dao
    abstract fun historyDao(): HistoryDao
}