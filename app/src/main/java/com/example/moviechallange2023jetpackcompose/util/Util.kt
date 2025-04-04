package com.example.moviechallange2023jetpackcompose.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

// Helper function to format numbers with dots as grouping separators.
fun formatNumber(number: Long): String {
    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = '.'
    }
    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(number)
}