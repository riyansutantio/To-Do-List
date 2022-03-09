package com.example.todolist.ui.components.textfield
import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputFieldComponent(
    text: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    label: String = "Some val",
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        modifier = modifier,
        singleLine = singleLine,
        label = {
            Text(text = label)
        },
        keyboardActions = keyboardActions,
    )
}

