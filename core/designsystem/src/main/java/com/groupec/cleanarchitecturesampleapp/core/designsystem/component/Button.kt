package com.groupec.cleanarchitecturesampleapp.core.designsystem.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.groupec.cleanarchitecturesampleapp.core.designsystem.R

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = stringResource(id = R.string.back_button))
    }
}