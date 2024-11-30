package com.example.icr_ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.icr_core.utils.Margin
import com.example.icr_ui.theme.IcregistrationsdkTheme
import com.example.icr_ui.theme.large
import com.example.icr_ui.theme.medium

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ICRFlatButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledContentColor: Color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
    disabledContainerColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    content: @Composable() (() -> Unit)? = null,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    fontWeight: FontWeight = FontWeight.SemiBold,
    fontSize: Int = 14,
    textAlign: TextAlign = TextAlign.Center,
    text: String? = null,
    loadingText: String? = "Loading",
    onClick: () -> Unit = {},
) {
    Surface(
        shape = shape,
        color = if (enabled && !isLoading) containerColor else disabledContainerColor,
        contentColor = if (enabled && !isLoading) contentColor else disabledContentColor,
        modifier = modifier
            .combinedClickable(
                onClick = { if (!isLoading) onClick() },
                onLongClick = onLongClick ?: onClick,
                onDoubleClick = onDoubleClick ?: onClick,
                enabled = enabled && !isLoading
            ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(large)
        ) {
            if (isLoading) {
                Row {
                    if (loadingText != null) {
                        ICRText(
                            text = loadingText,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = contentColor,
                                fontWeight = fontWeight,
                                fontSize = fontSize.sp,
                                textAlign = textAlign
                            )
                        )
                        Margin(value = medium)
                    }

                    CircularProgressIndicator(
                        color = contentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }

            } else {
                if (content != null) {
                    content()
                } else {
                    ICRText(
                        text = text ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (enabled) contentColor else disabledContentColor,
                            fontWeight = fontWeight,
                            fontSize = fontSize.sp,
                            textAlign = textAlign
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun ICRFlatButtonPreview() {
    IcregistrationsdkTheme() {
        ICRFlatButton(
            text = "Click me",
        )
    }


}
