package com.example.mortgagecalculator.ui.theme

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mortgagecalculator.MortgageScreen
import com.example.mortgagecalculator.R
import com.example.mortgagecalculator.data.Mortgage

@Composable
fun DoneScreen(
    viewModel: Mortgage, navController: NavHostController
) {
    val items = listOf(
        // Summary line 1: display amount
        Pair(stringResource(R.string.amount), viewModel.getFormattedAmount()),
        // Summary line 2: display number of years
        Pair(stringResource(R.string.years), viewModel.getYears()),
        // Summary line 3: display interest rate
        Pair(stringResource(R.string.interest_rate),viewModel.getFormattedRate()),
        // Summary line 4: display monthly payment
        Pair(stringResource(R.string.monthly_payment), viewModel.formattedMonthlyPayment()),
        // Summary line 5: display total payment
        Pair(stringResource(R.string.total_payment), viewModel.formattedTotalPayment()),
        )

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(Color(0xFF4855A2))
        ){
            Text(text = "MortgageV0", color = Color(0xFFFFFFFF), modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp), fontSize = 20.sp)
        }
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEach { item ->
                    Text(text = item.first, modifier = Modifier.padding(top = 16.dp), fontSize = 20.sp)
                }
            }
            Spacer(Modifier.width(40.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEach { item ->
                    Text(text = "${item.second}", modifier = Modifier.padding(top = 16.dp), fontSize = 20.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(44.dp))
        Button(
            modifier = Modifier,
            onClick = { navController.navigate("modify") }
        ) {
            Text(stringResource(R.string.modify))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDoneScreen() {
    val mockViewModel = Mortgage().apply {
        setAmount(100000f)
        setYears(30)
        setRate(0.035f)
    }

    val navController = rememberNavController() // Mock navController for preview

    DoneScreen(
        viewModel = mockViewModel,
        navController = navController,
    )
}