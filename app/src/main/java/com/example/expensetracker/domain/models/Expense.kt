package com.example.expensetracker.domain.models

data class Expense(
    val id: Int = 0,
    val amount: Double = 0.0,
    val category: String? = null,
    val note: String? = null,
    val dateMillis: Long = System.currentTimeMillis(),
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
)
