package com.example.expensetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.response.Response
import com.example.expensetracker.data.response.safeCall
import com.example.expensetracker.domain.models.Expense
import com.example.expensetracker.domain.usecases.AddExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase
) : ViewModel() {

    private val _getExpense = MutableStateFlow<Response<List<Expense>>>(Response.Idle)
    var getExpense: StateFlow<Response<List<Expense>>> = _getExpense.asStateFlow()

    private val _addExpense = MutableStateFlow<Response<Long>>(Response.Idle)
    val addExpense: StateFlow<Response<Long>> = _addExpense.asStateFlow()


    private fun getExpense() {
        viewModelScope.launch {
            //Todo
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            _addExpense.value = Response.Loading
            _addExpense.value = safeCall { addExpenseUseCase.invoke(expense) }
        }
    }

    fun resetAddExpenseState() {
        _addExpense.value = Response.Idle
    }


}