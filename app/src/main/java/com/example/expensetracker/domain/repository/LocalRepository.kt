package com.example.expensetracker.domain.repository

import com.example.expensetracker.domain.models.Expense

interface LocalRepository {
    suspend fun addExpense(expense: Expense) : Long
}