package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ProgressIndicator
import com.quehacerenzaragoza.soydezaragoza.core.BackHandler
import com.quehacerenzaragoza.soydezaragoza.core.viewModel
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ObtainDataState

object NewsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: NewsViewModel = viewModel()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(viewModel: NewsViewModel) {
        val newsState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getPostsByCategories()
        }

        Column {
            // Renderizado de posts según su estado
            when (val postsState = newsState.postsState) {
                is ObtainDataState.Loading -> {
                    ProgressIndicator()
                }

                is ObtainDataState.Success -> {
                    println("estado post" +postsState.data)
                }

                is ObtainDataState.Error -> {
                }

                ObtainDataState.Idle -> {
                }
            }
        }

        BackHandler(false, onBack = {
        })
    }
}