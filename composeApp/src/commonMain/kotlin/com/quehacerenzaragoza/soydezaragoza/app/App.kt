package com.quehacerenzaragoza.soydezaragoza.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.quehacerenzaragoza.soydezaragoza.AppTheme
import com.quehacerenzaragoza.soydezaragoza.di.init
import com.quehacerenzaragoza.soydezaragoza.presentation.screens.news.NewsScreen
import org.koin.compose.KoinApplication

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    KoinApplication(application = {
        init()
    }) {
        AppTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
            Navigator(NewsScreen)
        }
    }
}