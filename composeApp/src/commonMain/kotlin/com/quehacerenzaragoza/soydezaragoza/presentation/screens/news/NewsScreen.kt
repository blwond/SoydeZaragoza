package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import com.quehacerenzaragoza.soydezaragoza.core.BackHandler
import com.quehacerenzaragoza.soydezaragoza.core.viewModel

object NewsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: NewsViewModel = viewModel()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(viewModel: NewsViewModel) {
        val newsState by viewModel.state.collectAsState()

        BackHandler(false, onBack = {
        })
    }
}