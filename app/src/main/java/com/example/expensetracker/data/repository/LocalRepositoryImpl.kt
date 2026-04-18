package com.example.expensetracker.data.repository

import com.example.expensetracker.data.local.ExpenseDao
import com.example.expensetracker.data.mapper.toEntity
import com.example.expensetracker.domain.models.Expense
import com.example.expensetracker.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : LocalRepository {

    override suspend fun addExpense(expense: Expense) : Long {
        return expenseDao.insert(expense.toEntity())
    }


}