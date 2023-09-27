package com.example.translator_2.model.room

import androidx.room.*

@Dao
interface HistoryDao {
    //получить весь список слов
    @Query("SELECT * FROM HistoryEntity")
    suspend fun all(): List<HistoryEntity>

    //получить конкретное слово
    @Query("SELECT * FROM HistoryEntity WHERE word LIKE :word")
    suspend fun getDataByWord(word: String): HistoryEntity

    //Сохранить новое слово
    //OnConflict = OnConflictStrategy.IGNORE означает, что дубликаты не будут сохраняться
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)

    // Вставить список слов
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistoryEntity>)

    // Обновить слово
    @Update
    suspend fun update(entity: HistoryEntity)

    // Удалить слово
    @Delete
    suspend fun delete(entity: HistoryEntity)
}