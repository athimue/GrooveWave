package com.athimue.app.composable.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.CoverItem(
    painter: Painter
) {
    Image(
        painter = painter,
        contentDescription = "",
        alignment = Alignment.Center,
        modifier = Modifier
            .size(200.dp)
            .padding(top = 4.dp, bottom = 4.dp)
            .clip(RoundedCornerShape(10.dp))
            .padding(bottom = 3.dp)
            .align(Alignment.CenterHorizontally)
    )
}