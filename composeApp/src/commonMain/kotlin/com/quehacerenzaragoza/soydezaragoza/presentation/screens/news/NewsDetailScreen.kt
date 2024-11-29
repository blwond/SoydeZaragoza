package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.quehacerenzaragoza.soydezaragoza.core.viewModel
import com.quehacerenzaragoza.soydezaragoza.presentation.screens.news.NewsScreen.MainContent

object NewsDetailScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: NewsViewModel = viewModel()
        MainContent(viewModel)
    }
}