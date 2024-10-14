package com.example.mortgagecalculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mortgagecalculator.R
import com.example.mortgagecalculator.data.Mortgage

@Composable
fun ModifyScreen(
    viewModel: Mortgage,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var amount by rememberSaveable { mutableStateOf(viewModel.getAmount().toString()) }
    var interestRate by rememberSaveable { mutableStateOf(viewModel.getRate().toString()) }
    var selectedOption by rememberSaveable { mutableStateOf(viewModel.getYears().toString()) }

    val items = listOf(
        // Summary line 1: display number of years
        stringResource(R.string.years),
        // Summary line 2: display amount
        stringResource(R.string.amount),
        // Summary line 3: display interest rate
        stringResource(R.string.interest_rate),
    )

    val radioOptions = listOf(
        // Display number of years
        stringResource(R.string.ten),
        stringResource(R.string.fifteen),
        stringResource(R.string.thirty),
    )

    Column(
        modifier = modifier.fillMaxSize(),
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
            modifier = Modifier.padding(16.dp),
        ) {
            Column(

            ) {
                items.forEach { item ->
                    Text(text = item, fontSize = 20.sp, modifier = Modifier.padding(top = 44.dp))
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(44.dp))

                Row(
                    modifier = Modifier
                ) {
                    radioOptions.forEach { text ->

                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = { selectedOption = text }
                        )
                        Text(
                            text = text,
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.displayMedium.merge(),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                    // Input for the amount
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("Enter Amount") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Input for the interest rate
                    OutlinedTextField(
                        value = interestRate,
                        onValueChange = { interestRate = it },
                        label = { Text("Enter Interest Rate") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        Spacer(modifier = Modifier.height(44.dp))
        Button(
            modifier = Modifier,
            onClick = {
                // Parse inputs and trigger callback
                val parsedAmount = amount.toFloatOrNull() ?: 0f
                val parsedInterestRate = interestRate.toFloatOrNull() ?: 0f
                val parsedYears = when (selectedOption) {
                    radioOptions[0] -> 10
                    radioOptions[1] -> 15
                    else -> 30
                }
                // Update the ViewModel with new values
                viewModel.setAmount(parsedAmount)
                viewModel.setYears(parsedYears)
                viewModel.setRate(parsedInterestRate)

                // Navigate back to the DoneScreen or wherever necessary
                navController.navigate("summary")
            }
        ) {
            Text("Done")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewModifyScreen() {
    val mockViewModel = Mortgage().apply {
        setAmount(150000f)
        setYears(15)
        setRate(0.04f)
    }

    val navController = rememberNavController() // Mock navController for preview

    ModifyScreen(
        viewModel = mockViewModel,
        navController = navController,
        modifier = Modifier
    )
}