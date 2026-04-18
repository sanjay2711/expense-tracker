package com.example.expensetracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double = 0.0,
    val category: String? = null,
    val note: String? = null,
    val dateMillis: Long = System.currentTimeMillis(),
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
)