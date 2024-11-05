package com.quehacerenzaragoza.soydezaragoza.core

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(isEnabled: Boolean, onBack: () -> Unit)