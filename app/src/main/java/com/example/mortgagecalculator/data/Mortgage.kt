package com.example.mortgagecalculator.data

import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import kotlin.math.pow

class Mortgage : ViewModel() {
    private val MONEY: DecimalFormat = DecimalFormat("$#,##0.00")
    private var amount: Float = 100000f
    private var years: Int = 30
    private var rate: Float = 0.035f

    // Methods to update mortgage data
    fun setAmount(newAmount: Float) {
        if (newAmount >= 0) amount = newAmount
    }
    fun setYears(newYears: Int) {
        if (newYears >= 0) years = newYears
    }
    fun setRate(newRate: Float) {
        if (newRate >= 0) rate = newRate
    }

    // Methods to retrieve mortgage data
    fun getAmount(): Float = amount
    fun getFormattedAmount(): String = MONEY.format(amount)
    fun getYears(): Int = years
    fun getRate(): Float = rate
    fun getFormattedRate(): String = String.format("%.1f%%", rate*100)

    // Calculate mortgage-related values
    fun monthlyPayment(): Float {
        val mRate = rate / 12 // monthly interest rate
        val temp = (1 / (1 + mRate)).toDouble().pow((years * 12).toDouble())
        return amount * mRate / (1 - temp).toFloat()
    }

    fun formattedMonthlyPayment(): String = MONEY.format(monthlyPayment())
    fun totalPayment(): Float = monthlyPayment() * years * 12
    fun formattedTotalPayment(): String = MONEY.format(totalPayment())
}