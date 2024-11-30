package com.example.icr_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.icr_ui.theme.IcregistrationsdkTheme


@Composable
fun ICROutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    label: String? = null,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    placeholderColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    errorTrailingIcon: @Composable (() -> Unit)? = null,
    errorLeadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    validate: (String) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onDone: () -> Unit = {},
    onNext: () -> Unit = {},
    onPrevious: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    onSend: () -> Unit = {},
    onGo: () -> Unit = {},
    focusRequester: FocusRequester? = null,
    focusManager: FocusManager? = null,
    textStyle: TextStyle = TextStyle.Default.copy(textDirection = TextDirection.Ltr),
    hintStyle: TextStyle = TextStyle.Default.copy(
        fontSize = 16.sp, color = placeholderColor
    ),
) {

    val mLeadingIcon: @Composable (() -> Unit)? = if (isError) {
        errorLeadingIcon
    } else {
        leadingIcon
    }
    val mTrailingIcon: @Composable (() -> Unit)? = if (isError) {
        errorTrailingIcon
    } else {
        trailingIcon
    }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = borderColor,
            errorBorderColor = MaterialTheme.colorScheme.error,
        ),
        textStyle = textStyle,
        placeholder = if (placeholder != null) {
            {
                ICRText(
                    text = placeholder, style = hintStyle
                )
            }
        } else null,
        value = value,
        onValueChange = {
            onValueChange(it)
            validate(it)
        },
        shape = RoundedCornerShape(6.dp),
        label = if (label != null) {
            {
                ICRText(
                    text = label, style = hintStyle
                )
            }
        } else null,
        keyboardActions = KeyboardActions(onDone = {
            onDone()
            focusManager?.clearFocus()
        }, onNext = { onNext() }, onPrevious = { onPrevious() }, onSearch = {
            onSearch(
                value.trim()
            )
        }, onSend = { onSend() }, onGo = { onGo() }),
        visualTransformation = visualTransformation,
        isError = isError,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (isError) {
                supportingText?.invoke()
            }
        },
        maxLines = maxLines,
        leadingIcon = mLeadingIcon,
        trailingIcon = mTrailingIcon,

        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester())
    )

}


@Composable
fun ICRBasicOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    borderColor: Color = Color.Gray,
    placeholderColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    errorTrailingIcon: @Composable (() -> Unit)? = null,
    errorLeadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    validate: (String) -> Boolean = { false },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester? = null,
    focusManager: FocusManager? = null,
    textStyle: TextStyle = TextStyle.Default,
    hintStyle: TextStyle = TextStyle.Default.copy(
        fontSize = 16.sp, color = placeholderColor,
    ),
    onFocusChanged: (FocusState) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onDone: () -> Unit = {},
    onNext: () -> Unit = {},
    onPrevious: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    onSend: () -> Unit = {},
    onGo: () -> Unit = {},
) {
    var isFieldError by remember { mutableStateOf(isError) }
    val currentBorderColor = if (isFieldError) MaterialTheme.colorScheme.error else borderColor
    var isFocused by remember { mutableStateOf(false) }
    val mBorderColor = remember(currentBorderColor, isFocused) {
        if (isFocused) focusedBorderColor else currentBorderColor
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.2.dp, mBorderColor, RoundedCornerShape(6.dp))
            .background(Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isFieldError = validate(it)
            },
            singleLine = singleLine,
            maxLines = maxLines,
            textStyle = textStyle,
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (isFieldError && errorLeadingIcon != null) {
                        errorLeadingIcon()
                    } else if (!isFieldError && leadingIcon != null) {
                        leadingIcon()
                    }
                    if (value.isEmpty() && placeholder != null) {
                        Text(
                            text = placeholder, style = hintStyle, modifier = Modifier.alpha(0.5f),
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    if (isFieldError && errorTrailingIcon != null) {
                        errorTrailingIcon()
                    } else if (!isFieldError && trailingIcon != null) {
                        trailingIcon()
                    }

                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone()
                    focusManager?.clearFocus()
                },
                onNext = { onNext() },
                onPrevious = {
                    onPrevious()
                    focusManager?.clearFocus()
                },
                onSearch = {
                    onSearch(
                        value.trim()
                    )
                    focusManager?.clearFocus()
                },
                onSend = {
                    onSend()
                    focusManager?.clearFocus()
                },
                onGo = {
                    onGo()
                    focusManager?.clearFocus()
                },

                ),
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChanged(it)
                }
                .focusRequester(focusRequester ?: FocusRequester())
                .fillMaxWidth()
        )
    }
    if (isFieldError) {
        supportingText?.invoke()
    }
}


@Composable
@Preview(
    showSystemUi = false,
    locale = "ar",
    device = "spec:width=1080px,height=2340px,dpi=440",
    backgroundColor = 0xFFFFFFFF
)
private fun ICRCustomOutlinedTextFieldPreview() {
    val charLimit = 10
    fun validate(text: String): Boolean {
        return text.length > charLimit
    }

    val focusManager = LocalFocusManager.current

    IcregistrationsdkTheme() {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Scaffold { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {

                    ICROutlinedTextField(
                        value = "",
                        focusManager = focusManager,
                        onValueChange = {},
                        placeholder = "كود المستخدم",
                        modifier = Modifier.fillMaxWidth(),
                        isError = validate(""),
                        validate = ::validate,
                    )
                    ICRBasicOutlinedTextField(
                        value = "",
                        focusManager = focusManager,
                        onValueChange = {},
                        placeholder = "كود المستخدم",
                        modifier = Modifier.fillMaxWidth(),
                        isError = validate(""),
                        validate = ::validate,
                    )
                }
            }
        }
    }

}