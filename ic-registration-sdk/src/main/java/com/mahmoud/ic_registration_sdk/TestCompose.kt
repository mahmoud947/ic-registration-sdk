package com.mahmoud.ic_registration_sdk

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestCompose(modifier: Modifier = Modifier, name: String) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview
@Composable
private fun TestComposePreview() {
    TestCompose(name = "Mahmoud")
}