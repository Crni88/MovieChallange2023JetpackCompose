package com.example.moviechallange2023jetpackcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviechallange2023jetpackcompose.ui.theme.MovieShapes

@Composable
fun MovieChip(genre: String) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(20.dp)
            .clip(MovieShapes.medium)
            .background(color = Color.LightGray),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = genre,
                color = Color.Black,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Composable
fun RowScope.KeyFactsCard(
    title: String,
    value: String?
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(60.dp)
            .clip(MovieShapes.medium)
            .background(color = Color.LightGray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 16.dp),
        ) {
            Text(
                text = title,
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value ?: "",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}