package com.example.expensetracker.data.mapper

import com.example.expensetracker.data.local.ExpenseEntity
import com.example.expensetracker.domain.models.Expense
import java.util.Calendar

fun ExpenseEntity.toModel(): Expense {
    return Expense(id, amount, category, note,dateMillis,day,month,year)
}

fun Expense.toEntity(): ExpenseEntity {
    val (day, month, year) = dateMillis.toDateParts()
    return ExpenseEntity(
        id = id,
        amount = amount,
        category = category,
        note = note,
        dateMillis = dateMillis,
        day = day,
        month = month,
        year = year
    )
}

fun Long.toDateParts(): Triple<Int, Int, Int> {
    val calendar = Calendar.getInstance().apply { timeInMillis = this@toDateParts }
    return Triple(
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.YEAR)
    )
}