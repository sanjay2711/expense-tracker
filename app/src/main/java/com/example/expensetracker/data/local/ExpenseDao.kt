package com.example.expensetracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(expense: ExpenseEntity) : Long

    @Query("SELECT * FROM expenses")
    fun getAll(): Flow<List<ExpenseEntity>>

    @Delete
    suspend fun delete(expense: ExpenseEntity)
}