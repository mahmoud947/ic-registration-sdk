package com.example.icr_ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun ICRText(
    modifier: Modifier = Modifier,
    text: String,
    textDecoration: TextDecoration = TextDecoration.Companion.None,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLines: Int = Int.Companion.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Companion.Ellipsis
) {

    Text(
        modifier = modifier,
        text = text,
        overflow = overflow,
        style = style,
        maxLines = maxLines,
        textAlign = style.textAlign,
        textDecoration = textDecoration
    )


}



