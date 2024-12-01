package com.example.icr_ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.icr_core.utils.Margin
import com.example.icr_ui.theme.extraLarge
import com.example.icr_ui.theme.large
import com.example.icr_domain.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ICRBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    subTitle: String,
    positiveActionLabel: String? = null,
    onPositive: () -> Unit,
    iconContent: @Composable () -> Unit,
) {

    ModalBottomSheet(
        modifier = modifier.padding(bottom = 50.dp),
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ICRText(
                text = title,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor
                ),
            )
            iconContent()
            ICRText(
                modifier = Modifier.padding(horizontal = large),
                text = subTitle,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Margin(value = extraLarge)
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(45.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = onPositive
            ) {
                ICRText(text = positiveActionLabel ?: stringResource(R.string.ok))
            }
            Margin(value = extraLarge)
        }
    }
}