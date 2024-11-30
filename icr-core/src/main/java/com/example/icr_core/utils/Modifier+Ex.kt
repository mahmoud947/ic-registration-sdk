package com.example.icr_core.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun ColumnScope.Margin(value: Dp) = Spacer(modifier = Modifier.height(height = value))

@Composable
fun RowScope.Margin(value: Dp) = Spacer(modifier = Modifier.width(width = value))
