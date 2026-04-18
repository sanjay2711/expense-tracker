package com.example.expensetracker.domain.usecases

import com.example.expensetracker.domain.models.Expense
import com.example.expensetracker.domain.repository.LocalRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(expense: Expense) : Long {
        return localRepository.addExpense(expense)
    }
}