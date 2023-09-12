package com.athimue.app.composable.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PrimaryColorText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary
    )
}