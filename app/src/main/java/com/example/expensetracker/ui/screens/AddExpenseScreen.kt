package com.example.expensetracker.ui.screens


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.expensetracker.R
import com.example.expensetracker.ui.viewmodel.ExpenseViewModel
import com.example.expensetracker.data.response.Response
import com.example.expensetracker.domain.models.Expense
import androidx.compose.ui.graphics.Brush


//@Preview(showSystemUi = true)
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
                "Login",
                style = TextStyle(
                    fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.google_sans_bold))
                ),
            )

            OutlinedTextField(
                value = email,
                onValueChange = { it ->
                    email = it
                },
                label = {
                    Text(
                        "Email",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.google_sans_regular))
                        )
                    )
                },
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
                label = {
                    Text(
                        "Password",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.google_sans_regular))
                        )
                    )
                },
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
                Text(
                    "Submit",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.google_sans_medium))
                    )
                )
            }
            ExpenseCard()


        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ViewCompose() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            ExpenseSummaryCard()
        }
    }
}


@Composable
fun ExpenseCard(total: Long = 0, currentMonth: Long = 0) {

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(all = 8.dp),
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            val (totalExpenseText, totalExpense, expensePercentage, incomeText, savingsText) = createRefs()

            Text(
                "Total expenses - April 2026",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.google_sans_regular))
                ),
                modifier = Modifier.constrainAs(totalExpenseText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )

            Text(
                "₹ 10,000",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.google_sans_bold))
                ),
                modifier = Modifier.constrainAs(totalExpense) {
                    top.linkTo(totalExpenseText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.constrainAs(expensePercentage) {
                    top.linkTo(totalExpense.bottom, margin = 5.dp)
                    start.linkTo(parent.start)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_increase),
                    contentDescription = "Upward direction",
                    tint = Color(0xFF00E676),
                    modifier = Modifier.size(15.dp)
                )

                Text(
                    "8.3%",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.google_sans_medium))
                    ),
                    color = Color(0xFF00E676)
                )

                Text(
                    "compared to last month",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.google_sans_regular))
                    ),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(incomeText) {
                        top.linkTo(expensePercentage.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                    }
            ) {

                StatCard(label = "Income", value = "₹95,000")
                StatCard(label = "Savings", value = "₹52,420")
            }
        }
    }
}


@Composable
fun StatCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x813E3D3D)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_increase),
                    contentDescription = "Trend Up",
                    tint = Color(0xFF00E676), // Bright green color
                    modifier = Modifier.size(15.dp)
                )


                Text(
                    value,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.google_sans_bold))
                    )
                )
            }
        }
    }
}


val Purple700 = Color(0xFF5A51C2)
val Purple900 = Color(0xFF3C3489)
val Purple950 = Color(0xFF26215C)

@Composable
fun ExpenseSummaryCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Purple700, Purple900, Purple950)
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(24.dp)
    ) {
        Column {
            // Title
            Text(
                text = "Total expenses — April 2026",
                color = Color.White.copy(alpha = 0.65f),
                fontFamily = FontFamily(Font(R.font.google_sans_regular)),
                fontSize = 13.sp,
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Amount
            Text(
                text = "₹42,580",
                color = Color.White,
                fontSize = 38.sp,
                fontFamily = FontFamily(Font(R.font.google_sans_medium)),
                letterSpacing = (-1).sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                UpArrowIcon(color = Color(0xFF4FE032))
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = Color(0xFF4FE032),
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("8.3%")
                        }
                        withStyle(SpanStyle(color = Color.White.copy(alpha = 0.5f))) {
                            append(" compared to last month")
                        }
                    },
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.google_sans_regular))
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MiniStatCard(label = "Income", value = "₹95,000", modifier = Modifier.weight(1f))
                MiniStatCard(label = "Savings", value = "₹52,420", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun MiniStatCard(label: String, value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Column {
            Text(
                text = label,
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.google_sans_regular))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                UpArrowIcon(color = Color(0xFF5DCAA5), size = 6.dp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.google_sans_medium))
                )
            }
        }
    }
}

@Composable
fun UpArrowIcon(color: Color, size: Dp = 8.dp) {
    Canvas(modifier = Modifier.size(width = size, height = size * 1.3f)) {
        val path = Path().apply {
            moveTo(this@Canvas.size.width / 2f, 0f)
            lineTo(this@Canvas.size.width, this@Canvas.size.height)
            lineTo(0f, this@Canvas.size.height)
            close()
        }
        drawPath(path = path, color = color)
    }
}