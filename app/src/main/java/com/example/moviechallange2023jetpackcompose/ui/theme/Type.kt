package com.example.moviechallange2023jetpackcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moviechallange2023jetpackcompose.R

val customFontFamily = FontFamily(
    Font(R.font.sfprodisplay_regular, FontWeight.Normal)
)

val Typography = Typography(
    // All Movie Titles
    titleSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Single Movie Title
    titleLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // Labels
    labelLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    // Input Fields
    labelMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    // Error Message Label
    labelSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    ),

    // Key Facts
    bodySmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Regular Text
    bodyMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Chip Text
    displaySmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.5.sp
    )
)
