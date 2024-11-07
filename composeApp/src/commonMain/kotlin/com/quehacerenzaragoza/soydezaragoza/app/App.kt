package com.quehacerenzaragoza.soydezaragoza.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
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
            setSingletonImageLoaderFactory { context ->
                getAsyncImageLoader(context)
            }
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext)=
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
