package com.quehacerenzaragoza.soydezaragoza

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.quehacerenzaragoza.soydezaragoza.util.theme.DarkColorScheme
import com.quehacerenzaragoza.soydezaragoza.util.theme.LightColorScheme
import com.quehacerenzaragoza.soydezaragoza.util.theme.Typography

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}