package com.example.icr_ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.icr_domain.R
import androidx.compose.runtime.getValue

@Composable
fun ICRLottiAnimation(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    iterations: Int = LottieConstants.IterateForever,
    @androidx.annotation.RawRes
    rowLottie: Int = R.raw.warning_alert
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            rowLottie
        )
    )
    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = iterations,
        isPlaying = isPlaying
    )
    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}