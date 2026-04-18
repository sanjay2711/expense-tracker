package com.example.expensetracker.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.expensetracker.ui.viewmodel.ExpenseViewModel
import com.example.expensetracker.data.response.Response
import com.example.expensetracker.domain.models.Expense


@Preview(showSystemUi = true)
@Composable
fun AddExpenseScreen() {
    val expenseViewModel: ExpenseViewModel = hiltViewModel()
    val addExpenseState by expenseViewModel.addExpense.collectAsStateWithLifecycle()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->

        LaunchedEffect(addExpenseState) {
            when (val state = addExpenseState) {
                is Response.Success -> {
                    snackBarHostState.showSnackbar("Expense added successfully! ${state.data}")
                    expenseViewModel.resetAddExpenseState()
                }

                is Response.Error -> {
                    snackBarHostState.showSnackbar(state.message)
                    expenseViewModel.resetAddExpenseState()
                }

                else -> Unit
            }
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Login", style = TextStyle(
                    fontSize = 20.sp, fontWeight = FontWeight(500)
                )
            )

            OutlinedTextField(
                value = email,
                onValueChange = { it ->
                    email = it
                },
                label = { Text("Email") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { it ->
                    password = it
                },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            )
            Button(
                onClick = {
                    var expense = Expense(
                        id = 1, amount = 1000.0, category = "Recharge", note = "Mobile"
                    )
                    expenseViewModel.addExpense(expense)
                }
            ) {
                Text("Submit")
            }

        }
    }
}