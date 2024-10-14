package com.example.mortgagecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mortgagecalculator.data.Mortgage
import com.example.mortgagecalculator.ui.theme.DoneScreen
import com.example.mortgagecalculator.ui.theme.ModifyScreen
import com.example.mortgagecalculator.ui.theme.MortgageCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
            setContent {
            MortgageScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MortgageScreen() {
    val navController = rememberNavController()
    val mortgageViewModel: Mortgage = viewModel()
    NavHost(
        navController = navController,
        startDestination = "summary"
    ) {
        composable("summary") {
            DoneScreen(viewModel = mortgageViewModel, navController = navController)
        }
        composable("modify") {
            ModifyScreen(viewModel = mortgageViewModel, navController = navController)
        }
    }
}