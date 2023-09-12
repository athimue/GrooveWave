package com.athimue.app.composable.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitleText(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontFamily = FontFamily.Monospace,
        text = title
    )
}